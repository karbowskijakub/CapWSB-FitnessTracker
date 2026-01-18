package pl.wsb.fitnesstracker.training.internal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.wsb.fitnesstracker.training.api.Training;
import pl.wsb.fitnesstracker.training.api.TrainingProvider;
import pl.wsb.fitnesstracker.training.api.TrainingRepository;
import java.util.List;
import java.util.Optional;

/**
 * Service implementation for managing trainings.
 * Provides business logic for training operations.
 */
@Service
@RequiredArgsConstructor
class TrainingServiceImpl implements TrainingProvider {

    private final TrainingRepository trainingRepository;

    /**
     * Retrieves a training based on their ID.
     *
     * @param trainingId id of the training to be searched
     * @return An Optional containing the located Training, or Optional.empty() if not found
     */
    @Override
    public Optional<Training> getTraining(final Long trainingId) {
        return trainingRepository.findById(trainingId);
    }

    /**
     * Retrieves all trainings.
     *
     * @return List of all trainings
     */
    List<Training> getAllTrainings() {
        return trainingRepository.findAll();
    }

    /**
     * Retrieves all trainings for a specific user.
     *
     * @param userId ID of the user
     * @return List of trainings for the specified user
     */
    List<Training> getTrainingsForUser(Long userId) {
        return trainingRepository.findAll().stream()
                .filter(training -> training.getUser().getId().equals(userId))
                .toList();
    }
}