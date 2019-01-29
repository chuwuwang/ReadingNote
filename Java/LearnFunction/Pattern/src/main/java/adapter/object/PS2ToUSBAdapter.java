package adapter.object;

/**
 * 对象适配器, 将PS2接口装换成USB接口, 所以此类类型是USB接口(implements USBPort) + 成员变量ps2Port
 */
public class PS2ToUSBAdapter implements USBPort {

    private PS2Port ps2Port;

    public PS2ToUSBAdapter(PS2Port ps2Port) {
        this.ps2Port = ps2Port;
    }

    @Override
    public void workWithUSB() {
        System.out.println("转换的关键在这里,本来是");

        ps2Port.workWithPS2();

        System.out.println("经过你的转换，现在是USB工作中");
    }

}