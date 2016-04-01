package kerstein.weather;

import java.util.concurrent.LinkedBlockingQueue;

public class CheckWeatherThread extends Thread {

	private LinkedBlockingQueue<String> queue;

	public CheckWeatherThread(LinkedBlockingQueue<String> queue) {
		this.queue = queue;
	}

	public void run() {

		while (true) {
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			// NOTIFY BAD WEATHER
			notifyBadWeather();
		}
	}

	private void notifyBadWeather() {
		queue.add("Bad Weather");
	}
}
