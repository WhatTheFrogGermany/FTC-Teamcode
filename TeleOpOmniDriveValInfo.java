package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * Created by FTC2 on 20.01.2017.
 */

@TeleOp(name="Info: TeleOp DriveVal", group="info")
@Disabled
public class TeleOpOmniDriveValInfo extends TeleOpOmni3 {
    @Override
    public void loop() {
        super.loop();
        telemetry.addData("Scaled", Arrays.toString(super.scaled));


    }
}
