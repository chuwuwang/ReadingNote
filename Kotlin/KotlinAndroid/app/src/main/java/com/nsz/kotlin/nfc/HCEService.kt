package com.nsz.kotlin.nfc

import android.nfc.cardemulation.HostApduService
import android.os.Bundle
import com.nsz.kotlin.ux.common.ByteHelper
import com.nsz.kotlin.ux.common.CommonLog
import java.nio.charset.StandardCharsets

class HCEService : HostApduService() {

    companion object {
        private const val HCE_CARD_AID = "A00101010101"
        private const val SELECT_AID_HEADER = "00A40400"
        private val SW_OK = byteArrayOf(
            0X90.toByte(), 0x00.toByte()
        )
        private val SW_UNKNOWN: ByteArray = byteArrayOf(
            0x00.toByte(), 0x00.toByte()
        )
    }

    override fun onDeactivated(reason: Int) {
        CommonLog.e("onDeactivated: $reason")
    }

    override fun processCommandApdu(commandApdu: ByteArray?, extras: Bundle?): ByteArray {
        var hexString = ""
        if (commandApdu != null) {
            hexString = ByteHelper.bytes2HexString(commandApdu)
        }
        CommonLog.e("processCommand hexString: $hexString")
        val string = ByteHelper.hexString2String(hexString)
        CommonLog.e("processCommand string: $string")

        val dataIn = "https://www.baidu.com/"
        return dataIn.toByteArray(StandardCharsets.US_ASCII)
    }

    private fun buildSelectAIDCommand(): ByteArray {
        // Format: [CLASS | INSTRUCTION | PARAMETER 1 | PARAMETER 2 | LENGTH | DATA]
        val command = SELECT_AID_HEADER + String.format("%02X", HCE_CARD_AID.length / 2) + HCE_CARD_AID
        return ByteHelper.hexString2Bytes(command)
    }

}