package utility;

import utility.*;

public class KillAllProcess {
    public KillAllProcess() {
        try {
            Log.info("Killing Processes...");
            Runtime.getRuntime().exec(Constants.KILL_PROCESS);
            Log.info("Kill process executed");
        } catch (Exception e) {
            Log.warn("Failure in killing process due to: " + e.getMessage());
        }
    }
}
