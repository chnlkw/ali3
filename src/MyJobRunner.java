
import java.io.*;
import java.util.Iterator;

import com.aliyun.odps.Column;
import com.aliyun.odps.OdpsException;
import com.aliyun.odps.OdpsType;
import com.aliyun.odps.conf.Configurable;
import com.aliyun.odps.conf.Configuration;
import com.aliyun.odps.data.ArrayRecord;
import com.aliyun.odps.data.Record;
import com.aliyun.odps.data.TableInfo;
import com.aliyun.odps.mapred.conf.JobConf;
import com.aliyun.odps.mapred.utils.InputUtils;
import com.aliyun.odps.mapred.utils.OutputUtils;
import com.aliyun.odps.utils.*;
import com.aliyun.odps.mapred.Reducer;
//import com.aliyun.odps.mapred.*;
import com.aliyun.odps.mapred.RunningJob;
import com.aliyun.odps.mapred.Mapper;
import com.aliyun.odps.mapred.TaskContext;

/**
 * Job Runner
 */
interface JobRunner extends Configurable {
  RunningJob submit() throws OdpsException, IOException;
}

public class MyJobRunner implements JobRunner{
	
	public JobConf jobc ;
	private String inputFile ;
	private String outputFile ;
	private Mapper myMapper ;
	private Reducer myReducer ;
	private Reducer myCombiner ;
	private Mapper.TaskContext mapContext;
	private Reducer.TaskContext reduceContext;
	
	public RunningJob submit() throws IOException{
		TableInfo[] inputTable = InputUtils.getTables(jobc) ;
		TableInfo[] outputTable = OutputUtils.getTables(jobc) ;
		inputFile = inputTable[0].getTableName() ;
		outputFile = outputTable[0].getTableName() ;
		myMapper = ReflectionUtils.newInstance(jobc.getMapperClass(), jobc) ;
		myReducer = ReflectionUtils.newInstance(jobc.getReducerClass(), jobc) ;
		myCombiner = ReflectionUtils.newInstance(jobc.getCombinerClass(), jobc) ;
		
		mapContext = new MyMapperContext();
		myMapper.setup(mapContext);
		
		BufferedReader br = new BufferedReader(new FileReader(inputFile)) ;
		String line ;
		int recordNum = 0 ;
		//Column[] cls = {new Column("w1",OdpsType.STRING), new Column("w2",OdpsType.STRING)} ;
		//ArrayRecord ar = new ArrayRecord(cls) ;
		while((line = br.readLine()) != null){
			String[] ws = line.split(",") ;
			Column[] cls = new Column[ws.length];
			for(int i=0 ;i<ws.length ;i++){
				cls[i] = new Column("word",OdpsType.STRING) ;
			}
			ArrayRecord ar = new ArrayRecord(cls) ;
			for(int i=0 ;i<ws.length; i++){
				ar.setString(i, ws[i]);
			}
			myMapper.map(recordNum++, ar, mapContext);
		}
		br.close();
		reduceContext = new MyReducerContext();
		myReducer.setup(reduceContext);
		
		return null ;
	}
	
	public MyJobRunner(){
		//System.out.println("no parameter init") ;
	}
	

	public Configuration getConf() {
		// TODO Auto-generated method stub
		
		return null;
	}

	public void setConf(Configuration arg0) {
		// TODO Auto-generated method stub
		//System.out.println("setConf") ;
		jobc = (JobConf)arg0 ;
		
	}


	

	
}
