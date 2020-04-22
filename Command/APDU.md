### APDU指令示例
```
public void selectPPSE() throws Exception {
	byte CLA    = (byte) 0x00;
	byte INS    = (byte) 0xA4;
	byte P1     = (byte) 0x04;
	byte P2     = (byte) 0x00;
	byte[] data = ByteUtil.hexStr2Bytes("325041592E5359532E4444463031");
	byte Lc     = (byte) data.length;

	byte[] inData = new byte[data.length + 5];
	inData[0] = CLA;
	inData[1] = INS;
	inData[2] = P1;
	inData[3] = P2;
	inData[4] = Lc;

	System.arraycopy(data, 0, inData, 5, data.length);

	byte[] outData = new byte[512];
	int result = MyApplication.mReadCardOptV2.transmitApdu(AidlConstantsV2.CardType.MIFARE.getValue(), inData, outData);
	if (result > 0) {
		String hexStr = ByteUtil.bytes2HexStr(outData);
		LogUtil.e(Constant.TAG, "outData: " + hexStr);
	}
}
```