package org.example.target;

import org.example.target.models.TargetModel;

import java.io.IOException;
import java.util.Set;

public interface ModelReader {

    Set<TargetModel> read() throws IOException;
}
