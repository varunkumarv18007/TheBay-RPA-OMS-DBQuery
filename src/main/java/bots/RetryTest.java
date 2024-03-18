public class RetryTest {

    public static void main(String[] args) {
        // Call the method with retry
        retryMethod();
    }

    public static void retryMethod() {
        int maxRetries = 3;
        int retryCount = 0;
        boolean success = false;

        while (!success && retryCount < maxRetries) {
            try {
                // Call the method you want to retry
                //Actions
                methodToRetry();
                success = true; // If the method call doesn't throw an exception, mark success
            } catch (Exception e) {
                retryCount++;
                System.out.println("Retry #" + retryCount + ": " + e.getMessage());
                // You may want to add a delay here before retrying, using Thread.sleep() for example
            }
        }

        if (!success) {
            System.out.println("Failed after " + maxRetries + " retries.");
        }
    }

    // Example method that may throw an exception
    public static void methodToRetry() throws Exception {
        // Replace this with the actual method you want to retry
        // For demonstration purposes, let's say this method sometimes throws an exception
        if (Math.random() < 0.5) {
            throw new Exception("Simulated error occurred");
        }
        System.out.println("Method executed successfully");
    }
}
