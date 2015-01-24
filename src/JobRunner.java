import com.aliyun.odps.mapred.RunningJob;

public class JobRunner {
	
	public RunningJob submit(){
		RunningJob tmp = new MyRunningJob();
		
		return tmp ;
	}
}
