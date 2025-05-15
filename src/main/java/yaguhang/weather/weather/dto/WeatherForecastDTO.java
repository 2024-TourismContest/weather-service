package yaguhang.weather.weather.dto;

import lombok.*;

import java.util.List;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WeatherForecastDTO {
    private Integer pageIndex;
    private Integer size;
//    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String date;
    private List<WeatherForecastPerHourDTO> weathers;
}
