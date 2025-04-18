package az.edu.turingacademybackend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FaqEntity {

    @ElementCollection
    private List<String> questions;

    @ElementCollection
    private List<String> answers;
}
