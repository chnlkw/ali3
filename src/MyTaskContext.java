import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.Iterator;

import com.aliyun.odps.Column;
import com.aliyun.odps.counter.Counter;
import com.aliyun.odps.data.ArrayRecord;
import com.aliyun.odps.data.Record;
import com.aliyun.odps.data.TableInfo;
import com.aliyun.odps.mapred.Mapper;
import com.aliyun.odps.mapred.Reducer;
import com.aliyun.odps.mapred.TaskContext;
import com.aliyun.odps.mapred.TaskId;
import com.aliyun.odps.mapred.conf.JobConf;


public class MyTaskContext implements TaskContext {

	public Class<? extends Reducer> getCombinerClass()
			throws ClassNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	public String[] getGroupingColumns() {
		// TODO Auto-generated method stub
		return null;
	}

	public JobConf getJobConf() {
		// TODO Auto-generated method stub
		return null;
	}

	public Column[] getMapOutputKeySchema() {
		// TODO Auto-generated method stub
		return null;
	}

	public Column[] getMapOutputValueSchema() {
		// TODO Auto-generated method stub
		return null;
	}

	public Class<? extends Mapper> getMapperClass()
			throws ClassNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	public int getNumReduceTasks() {
		// TODO Auto-generated method stub
		return 0;
	}

	public Class<? extends Reducer> getReducerClass()
			throws ClassNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	public Record createMapOutputKeyRecord() throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	public Record createMapOutputValueRecord() throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	public Record createOutputKeyRecord() throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	public Record createOutputRecord() throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	public Record createOutputRecord(String arg0) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	public Record createOutputValueRecord() throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	public Counter getCounter(Enum<?> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public Counter getCounter(String arg0, String arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	public TableInfo[] getOutputTableInfo() throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	public TaskId getTaskID() {
		// TODO Auto-generated method stub
		return null;
	}

	public void progress() {
		// TODO Auto-generated method stub

	}

	public BufferedInputStream readResourceFileAsStream(String arg0)
			throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	public Iterator<Record> readResourceTable(String arg0) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	public void write(Record arg0) throws IOException {
		// TODO Auto-generated method stub

	}

	public void write(Record arg0, String arg1) throws IOException {
		// TODO Auto-generated method stub

	}

	public void write(Record arg0, Record arg1) throws IOException {
		// TODO Auto-generated method stub

	}

}
