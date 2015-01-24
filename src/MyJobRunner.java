import com.aliyun.odps.conf.Configuration;
import com.aliyun.odps.mapred.RunningJob;
import com.aliyun.odps.utils.*;
import com.aliyun.odps.mapred.*;
import com.aliyun.odps.mapred.conf.JobConf;
import com.aliyun.odps.OdpsException;
import com.aliyun.odps.conf.Configurable;

/**
 * Job Runner
 */
interface JobRunner extends Configurable {
  RunningJob submit() throws OdpsException;
}

public class MyJobRunner implements JobRunner{
	
	public Configuration jobc ;
	
	public RunningJob submit(){
		RunningJob tmp = new MyRunningJob();
		//jobc = (JobConf) this.
		
		return tmp ;
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
		System.out.println("setConf") ;
		jobc = arg0 ;
		
	}

	

	
}
