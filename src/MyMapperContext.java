import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;

import com.aliyun.odps.Column;
import com.aliyun.odps.OdpsType;
import com.aliyun.odps.counter.Counter;
import com.aliyun.odps.data.ArrayRecord;
import com.aliyun.odps.data.Record;
import com.aliyun.odps.data.TableInfo;
import com.aliyun.odps.mapred.Mapper;
import com.aliyun.odps.mapred.Mapper.TaskContext;
import com.aliyun.odps.mapred.Reducer;
import com.aliyun.odps.mapred.TaskId;
import com.aliyun.odps.mapred.conf.JobConf;


public class MyMapperContext implements TaskContext {

	public ArrayList<MyEntry<String, Long>> buf;
	public ArrayList<String> fileList;
	public int currentLen = 0;
	
	public static int FILE_LEN = 100;// * 1024 * 1024;
	public static final String FILE_PREFIX = "TMPFILE"; 
	
	public MyMapperContext()
	{
		buf = new ArrayList<MyEntry<String, Long>>();
		fileList = new ArrayList<String>();
	}
	
	void flushBuf2File() throws IOException {
		// sort
		Collections.sort(buf, new Comparator<MyEntry<String, Long>>() {
			public int compare(MyEntry<String, Long> o1,
					MyEntry<String, Long> o2) {
				String a = o1.getKey();
				String b = o2.getKey();
				return a.compareTo(b);
			}
		});
		// write to file
		String filename = FILE_PREFIX + fileList.size();
		BufferedWriter writer = new BufferedWriter(new FileWriter(filename)) ;
		for (int i = 0; i < buf.size(); i ++) {
			MyEntry<String, Long> e = buf.get(i);
			writer.write(e.getKey() + " " + e.getValue() + "\n");
		}
		writer.close();
		fileList.add(filename);
		// clear buf
		currentLen = 0;
		buf.clear();
	}
	
	public Record createMapOutputKeyRecord() throws IOException {
		// TODO Auto-generated method stub
		return new ArrayRecord(new Column[] { new Column("key", OdpsType.STRING) });
	}

	public Record createMapOutputValueRecord() throws IOException {
		// TODO Auto-generated method stub
		return new ArrayRecord(new Column[] { new Column("value", OdpsType.BIGINT) });
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
		String s = arg0.getString(0);
		buf.add(new MyEntry<String, Long>(s, arg1.getBigint(0)));
		currentLen += s.length();
		if (currentLen > FILE_LEN)
			flushBuf2File();
	}

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

	public Record getCurrentRecord() {
		// TODO Auto-generated method stub
		return null;
	}

	public long getCurrentRecordNum() {
		// TODO Auto-generated method stub
		return 0;
	}

	public TableInfo getInputTableInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean nextRecord() {
		// TODO Auto-generated method stub
		return false;
	}

}
