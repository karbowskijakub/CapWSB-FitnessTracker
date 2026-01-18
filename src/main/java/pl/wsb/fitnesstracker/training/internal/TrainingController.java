package pl.wsb.fitnesstracker.training.internal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.wsb.fitnesstracker.training.api.TrainingDto;
import java.util.List;

/**
 * REST Controller for training-related operations.
 * Provides HTTP endpoints for managing trainings.
 */
@RestController
@RequestMapping("/v1/trainings")
@RequiredArgsConstructor
class TrainingController {

    private final TrainingServiceImpl trainingService;
    private final TrainingMapper trainingMapper;

    /**
     * Retrieves all trainings in the system.
     *
     * @return ResponseEntity with list of all trainings
     */
    @GetMapping
    public ResponseEntity<List<TrainingDto>> getAllTrainings() {
        List<TrainingDto> trainings = trainingService.getAllTrainings()
                .stream()
                .map(trainingMapper::toDto)
                .toList();
        return ResponseEntity.ok(trainings);
    }


    /**
     * Retrieves all trainings for a specific user.
     *
     * @param userId ID of the user
     * @return ResponseEntity with list of trainings for the specified user
     */
    @GetMapping("/{userId}")
    public ResponseEntity<List<TrainingDto>> getTrainingsForUser(@PathVariable Long userId) {
        List<TrainingDto> trainings = trainingService.getTrainingsForUser(userId)
                .stream()
                .map(trainingMapper::toDto)
                .toList();
        return ResponseEntity.ok(trainings);
    }
}