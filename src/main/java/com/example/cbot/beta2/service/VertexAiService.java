// package com.example.cbot.beta2.service;

// import com.google.cloud.aiplatform.v1.EndpointName;
// import com.google.cloud.aiplatform.v1.PredictionServiceClient;
// import com.google.cloud.aiplatform.v1.PredictRequest;
// import com.google.cloud.aiplatform.v1.PredictResponse;
// import com.google.protobuf.Value;
// import com.google.protobuf.util.Values;
// import org.springframework.stereotype.Service;
// import java.io.IOException;

// @Service
// public class VertexAiService {

//     public String predictText(String projectId, String location, String endpointId, String textPrompt) throws IOException {
//         EndpointName endpointName = EndpointName.of(projectId, location, endpointId);
//         try (PredictionServiceClient client = PredictionServiceClient.create()) {
//             Value payload = Values.of(textPrompt);
//             PredictRequest request = PredictRequest.newBuilder()
//                     .setEndpoint(endpointName.toString())
//                     .addInstances(payload)
//                     .build();

//             PredictResponse response = client.predict(request);
//             return response.getDeployedModelId(); // Adjust this to fetch the actual text output based on your model's response structure
//         }
//     }
// }
