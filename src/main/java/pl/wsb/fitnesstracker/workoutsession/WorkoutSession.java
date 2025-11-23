package pl.wsb.fitnesstracker.workoutsession;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import pl.wsb.fitnesstracker.training.api.Training;

@Entity
@Table(name = "workout_session")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class WorkoutSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Nullable
    private Long id;

    @OneToOne
    @JoinColumn(name = "training_id", nullable = false, unique = true)
    private Training training;

    @Column(nullable = false)
    private String timestamp;

    @Column(name = "start_latitude", nullable = false)
    private Double startLatitude;

    @Column(name = "start_longitude", nullable = false)
    private Double startLongitude;

    @Column(name = "end_latitude", nullable = false)
    private Double endLatitude;

    @Column(name = "end_longitude", nullable = false)
    private Double endLongitude;

    @Column(name = "altitude", nullable = false)
    private Double altitude;

    public WorkoutSession(
            final Training training,
            final String timestamp,
            final Double startLatitude,
            final Double startLongitude,
            final Double endLatitude,
            final Double endLongitude,
            final Double altitude) {
        this.training = training;
        this.timestamp = timestamp;
        this.startLatitude = startLatitude;
        this.startLongitude = startLongitude;
        this.endLatitude = endLatitude;
        this.endLongitude = endLongitude;
        this.altitude = altitude;
    }
}