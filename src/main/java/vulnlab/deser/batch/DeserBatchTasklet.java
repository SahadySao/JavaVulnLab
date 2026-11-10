package vulnlab.deser.batch;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

/** Batch : reprise d'un état sérialisé depuis un dossier d'échange. Attendu dans « aVerifier ». */
public class DeserBatchTasklet implements Tasklet {
    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        String path = (String) chunkContext.getStepContext().getJobParameters().get("reprise");
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path))) {
            ois.readObject();
        }
        return RepeatStatus.FINISHED;
    }
}
