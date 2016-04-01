package kerstein.weather;

import java.util.concurrent.LinkedBlockingQueue;

public class Main {

	public static void main(String[] args) {
		LinkedBlockingQueue<String> queue=new LinkedBlockingQueue<String>(); //allows thread to block when reading from this queue
		NotifyWeatherThread notifyWeather = new NotifyWeatherThread(queue);
		CheckWeatherThread checkWeather = new CheckWeatherThread(queue);
		notifyWeather.start();
		checkWeather.start();

	}

}
