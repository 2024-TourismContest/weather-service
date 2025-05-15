package yaguhang.weather.weather.scheduler;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import yaguhang.weather.weather.application.WeatherForecastService;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
@RequiredArgsConstructor
public class WeatherForecastScheduler {

    private final StadiumRepository stadiumRepository;
    private final WeatherForecastService weatherForecastService;

    @Scheduled(cron = "10 10 2,5,8,11,14,17,20,23 * * ?")
//    @Scheduled(cron = "0 * * * * ?")
    public void fetchShortTermWeatherForecastData() throws IOException {

        List<Stadium> stadiumList = stadiumRepository.findAll();

        LocalDateTime now = LocalDateTime.now();

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH00");

        String baseDate = now.format(dateFormatter);
        String baseTime = now.format(timeFormatter);
//        baseTime = "1400";
//        System.out.println("baseTime = " + baseTime);

        for (Stadium stadium : stadiumList) {
            int nx = stadium.getNx(); // 경기장 X 좌표
            int ny = stadium.getNy(); // 경기장 Y 좌표
            weatherForecastService.fetchAndSaveShortTermForecastData(baseDate, baseTime, nx, ny);
        }
    }
}