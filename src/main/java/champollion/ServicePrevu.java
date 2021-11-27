package champollion;

public class ServicePrevu {
	private final UE myUE;
	private final Enseignant myEnseignant;

	private int volumeCM;
	private int volumeTD;
	private int volumeTP;

	public ServicePrevu(Enseignant e, UE ue, Integer volumeCM, Integer volumeTD, Integer volumeTP){
		if (e==null || ue==null || volumeCM==null || volumeTD==null || volumeTP==null){
			throw new NullPointerException();
		}
		myEnseignant=e;
		myUE=ue;
		this.volumeCM=volumeCM;
		this.volumeTD=volumeTD;
		this.volumeTP=volumeTP;
	}

	

	public UE getMyUE() {
		return myUE;
	}

	public int getVolumeCM() {
		return volumeCM;
	}

	public void setVolumeCM(int volumeCM) {
		this.volumeCM = volumeCM;
	}

	public int getVolumeTD() {
		return volumeTD;
	}

	public void setVolumeTD(int volumeTD) {
		this.volumeTD = volumeTD;
	}

	public int getVolumeTP() {
		return volumeTP;
	}

	public void setVolumeTP(int volumeTP) {
		this.volumeTP = volumeTP;
	}

	

}
