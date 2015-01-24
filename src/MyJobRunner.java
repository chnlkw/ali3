
import java.io.IOException;
import java.util.Iterator;

import com.aliyun.odps.Column;
import com.aliyun.odps.OdpsException;
import com.aliyun.odps.OdpsType;
import com.aliyun.odps.conf.Configurable;
import com.aliyun.odps.conf.Configuration;
import com.aliyun.odps.data.Record;
import com.aliyun.odps.data.TableInfo;
import com.aliyun.odps.mapred.conf.JobConf;
import com.aliyun.odps.mapred.utils.InputUtils;
import com.aliyun.odps.mapred.utils.OutputUtils;
import com.aliyun.odps.utils.*;
import com.aliyun.odps.mapred.*;

/**
 * Job Runner
 */
interface JobRunner extends Configurable {
  RunningJob submit() throws OdpsException;
}

public class MyJobRunner implements JobRunner{
	
	public JobConf jobc ;
	private String inputFile ;
	private String outputFile ;
	private Mapper myMapper ;
	private Reducer myReducer ;
	private Reducer myCombiner ;
	
	
	public RunningJob submit(){
		TableInfo[] inputTable = InputUtils.getTables(jobc) ;
		TableInfo[] outputTable = OutputUtils.getTables(jobc) ;
		inputFile = inputTable[0].getTableName() ;
		outputFile = outputTable[0].getTableName() ;
		myMapper = ReflectionUtils.newInstance(jobc.getMapperClass(), jobc) ;
		myReducer = ReflectionUtils.newInstance(jobc.getReducerClass(), jobc) ;
		myCombiner = ReflectionUtils.newInstance(jobc.getCombinerClass(), jobc) ;
		return null ;
	}
	
	public MyJobRunner(){
		System.out.println("no parameter init") ;
	}
	
	public void init(JobConf jc){
		jobc = jc ;
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
