package pl.wsb.fitnesstracker.training.api;
import pl.wsb.fitnesstracker.training.internal.ActivityType;
import pl.wsb.fitnesstracker.user.api.UserDto;
import java.util.Date;

/**
 * Data Transfer Object for Training entity.
 * Used to transfer training data between layers.
 */
public class TrainingDto {

    private Long id;
    private UserDto user;
    private Date startTime;
    private Date endTime;
    private ActivityType activityType;
    private double distance;
    private double averageSpeed;

    /**
     * Default constructor.
     */
    public TrainingDto() {
    }

    /**
     * Constructor creating DTO from Training entity.
     *
     * @param training Training entity to convert
     */
    public TrainingDto(Training training) {
        this.id = training.getId();
        this.user = new UserDto(
                training.getUser().getId(),
                training.getUser().getFirstName(),
                training.getUser().getLastName(),
                training.getUser().getBirthdate(),
                training.getUser().getEmail()
        );
        this.startTime = training.getStartTime();
        this.endTime = training.getEndTime();
        this.activityType = training.getActivityType();
        this.distance = training.getDistance();
        this.averageSpeed = training.getAverageSpeed();
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public ActivityType getActivityType() {
        return activityType;
    }

    public void setActivityType(ActivityType activityType) {
        this.activityType = activityType;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double getAverageSpeed() {
        return averageSpeed;
    }

    public void setAverageSpeed(double averageSpeed) {
        this.averageSpeed = averageSpeed;
    }
}