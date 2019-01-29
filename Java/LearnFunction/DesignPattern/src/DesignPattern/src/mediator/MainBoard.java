package mediator;

public class MainBoard extends Mediator {

    private CPU cpu;
    private CDDevice cdDevice;
    private SoundCard soundCard;
    private GraphicsCard graphicsCard;

    @Override
    public void changed(Colleague c) {
        if (c == cdDevice) {
            CDDevice cd = (CDDevice) c;
            handleCD(cd);
        } else if (c == cpu) {
            CPU cpu = (CPU) c;
            handleCPU(cpu);
        }
    }

    private void handleCD(CDDevice cdDevice) {
        String read = cdDevice.read();
        cpu.decodeData(read);
    }

    private void handleCPU(CPU cpu) {
        String dataSound = cpu.getDataSound();
        String dataVideo = cpu.getDataVideo();
        soundCard.soundPlay(dataSound);
        graphicsCard.videoPlay(dataVideo);
    }

    public void setCpu(CPU cpu) {
        this.cpu = cpu;
    }

    public void setCdDevice(CDDevice cdDevice) {
        this.cdDevice = cdDevice;
    }

    public void setSoundCard(SoundCard soundCard) {
        this.soundCard = soundCard;
    }

    public void setGraphicsCard(GraphicsCard graphicsCard) {
        this.graphicsCard = graphicsCard;
    }

}
