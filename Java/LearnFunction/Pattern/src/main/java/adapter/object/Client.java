package adapter.object;

public class Client {

    //////////////////////////////////////////////////////////////////////
    //																	//
    //		去年买了一个本本，另外给本本配了罗技G1光电套。坑爹的是，光电套		//
    //		的鼠标是USB接口，键盘是PS2接口，键盘是PS2接口，可我的本本却没		//
    //		有PS2接口啊。于是跑到市场，淘了一个转接器。						//
    //																	//
    //////////////////////////////////////////////////////////////////////

    public static void main(String[] args) {
        // 1.我现在有一个PS2接口
        PS2Port ps2Port = new PS2Port() {

            @Override
            public void workWithPS2() {
                System.out.println("PS2工作中");
            }

        };

        // 2.但是我需要的是一个USB接口啊, 对我(client)来说, 我只认识USB接口
        // 3.经过PS2ToUSB的转换, PS2接口变成了USB接口
        USBPort ps2ToUsbPort = new PS2ToUSBAdapter(ps2Port);

        ps2ToUsbPort.workWithUSB();
    }

}