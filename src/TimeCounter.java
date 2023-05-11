public class TimeCounter extends Thread{

    @Override
    public void run() {
        while(!Thread.interrupted()) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                break;
            }
            OknoGry.time++;
            OknoGry.timeLabel.setText("Time: " + OknoGry.time);
        }
    }
}
