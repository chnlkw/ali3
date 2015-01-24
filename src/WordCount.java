

import java.io.IOException;
import java.util.Iterator;

import com.aliyun.odps.Column;
import com.aliyun.odps.OdpsType;
import com.aliyun.odps.data.Record;
import com.aliyun.odps.data.TableInfo;
import com.aliyun.odps.mapred.conf.JobConf;
import com.aliyun.odps.mapred.utils.InputUtils;
import com.aliyun.odps.mapred.utils.OutputUtils;
import com.aliyun.odps.utils.*;
import com.aliyun.odps.mapred.*;

/**
 * This is an example ODPS Map/Reduce application. It reads the input table, map
 * each column into words and counts them. The output is a locally sorted list
 * of words and the count of how often they occurred.
 * <p>
 * To run: mapreduce -libjars mapreduce-examples.jar
 * com.aliyun.odps.mapreduce.examples.WordCount <i>in-tbl</i> <i>out-tbl</i>
 */
public class WordCount {

  /**
   * Counts the words in each record. For each record, emit each column as
   * (<b>word</b>, <b>1</b>).
   */
  public static class TokenizerMapper implements Mapper {

    Record word;
    Record one;

    
    public void setup(TaskContext context) throws IOException {
      word = context.createMapOutputKeyRecord();
      one = context.createMapOutputValueRecord();
      one.set(0, 1L);
    }

    public void map(long recordNum, Record record, TaskContext context) throws IOException {
      for (int i = 0; i < record.getColumnCount(); i++) {
        String[] words = record.get(i).toString().split("\\s+");
        for (String w : words) {
          word.set(0, w);
          context.write(word, one);
        }
      }
    }

    public void cleanup(TaskContext context) throws IOException {
      
    }
  }

  /**
   * A combiner class that combines map output by sum them.
   */
  public static class SumCombiner implements Reducer {
    private Record count;

    public void setup(TaskContext context) throws IOException {
      count = context.createMapOutputValueRecord();
    }

    public void reduce(Record key, Iterator<Record> values, TaskContext context) throws IOException {
      long c = 0;
      while (values.hasNext()) {
        Record val = values.next();
        c += (Long) val.get(0);
      }
      count.set(0, c);
      context.write(key, count);
    }

    public void cleanup(TaskContext context) throws IOException {

    }
  }

  /**
   * A reducer class that just emits the sum of the input values.
   */
  public static class SumReducer implements Reducer {
    private Record result;

    public void setup(TaskContext context) throws IOException {
      result = context.createOutputRecord();
    }

    public void reduce(Record key, Iterator<Record> values, TaskContext context) throws IOException {
      long count = 0;
      while (values.hasNext()) {
        Record val = values.next();
        count += (Long) val.get(0);
      }
      result.set(0, key.get(0));
      result.set(1, count);
      context.write(result);
    }

    public void cleanup(TaskContext context) throws IOException {

    }
  }

  public static void main(String[] args) throws Exception {
    if (args.length != 2) {
      System.err.println("Usage: wordcount <in_table> <out_table>");
      System.exit(2);
    }

    JobConf job = new JobConf();
    job.setMapperClass(TokenizerMapper.class);
    job.setCombinerClass(SumCombiner.class);
    job.setReducerClass(SumReducer.class);
    job.setMapOutputKeySchema(new Column[] { new Column("word", OdpsType.STRING) });
    job.setMapOutputValueSchema(new Column[] { new Column("count", OdpsType.BIGINT) });

    InputUtils.addTable(TableInfo.builder().tableName(args[0]).build(), job);
    OutputUtils.addTable(TableInfo.builder().tableName(args[1]).build(), job);

    Class<? extends JobRunner> runnerClz = (Class<? extends JobRunner>) Class.forName(System
        .getProperty("job.runner.class", "JobRunner"));
    
    JobRunner runner = ReflectionUtils.newInstance(runnerClz, job);
    RunningJob rj = runner.submit();
    rj.waitForCompletion();
  }
}
