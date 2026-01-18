package pl.wsb.fitnesstracker.training.internal;
import org.springframework.stereotype.Component;
import pl.wsb.fitnesstracker.training.api.Training;
import pl.wsb.fitnesstracker.training.api.TrainingDto;

/**
 * Mapper component for converting between Training entities and DTOs.
 */
@Component
class TrainingMapper {

    /**
     * Converts Training entity to TrainingDto.
     *
     * @param training Training entity to convert
     * @return TrainingDto object
     */
    TrainingDto toDto(Training training) {
        return new TrainingDto(training);
    }
}