package mediator;

public class CPU extends Colleague {

    private String dataVideo;
    private String dataSound;

    public CPU(Mediator mediator) {
        super(mediator);
    }

    public String getDataVideo() {
        return dataVideo;
    }

    public String getDataSound() {
        return dataSound;
    }

    public void decodeData(String data) {
        String[] tmp = data.split(",");
        dataVideo = tmp[0];
        dataSound = tmp[1];
        mediator.changed(this);
    }

}
