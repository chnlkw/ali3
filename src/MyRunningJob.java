import java.io.IOException;

import com.aliyun.odps.counter.Counters;
import com.aliyun.odps.mapred.JobStatus;
import com.aliyun.odps.mapred.RunningJob;

public class MyRunningJob implements RunningJob{

	public Counters getCounters() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getDiagnostics() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getInstanceID() {
		// TODO Auto-generated method stub
		return null;
	}

	public JobStatus getJobStatus() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isComplete() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isSuccessful() {
		// TODO Auto-generated method stub
		return false;
	}

	public void killJob() {
		// TODO Auto-generated method stub
		
	}

	public float mapProgress() throws IOException {
		// TODO Auto-generated method stub
		return 0;
	}

	public float reduceProgress() throws IOException {
		// TODO Auto-generated method stub
		return 0;
	}

	public void waitForCompletion() {
		// TODO Auto-generated method stub
		
	}

}
