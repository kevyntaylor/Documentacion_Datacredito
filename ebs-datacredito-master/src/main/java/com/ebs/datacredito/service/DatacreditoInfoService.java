package com.ebs.datacredito.service;

import com.ebs.datacredito.model.Param;
import com.ebs.datacredito.repository.IParamRepository;
import com.ebs.datacredito.service.interfaces.IDatacreditoInfoService;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
@Service
public class DatacreditoInfoService implements IDatacreditoInfoService {

    private final IParamRepository paramRepository;

    @Autowired
    public DatacreditoInfoService(IParamRepository paramRepository) {
        this.paramRepository = paramRepository;
    }

    @Override
    public String getDatacreditoInfo(String identifiction, String primerApellido, String tipoIdentificacion)
            throws IOException {

        StringBuilder urlBuilder = new StringBuilder();
        urlBuilder.append(
                String.format("http://python_client_container:8091/datacredito/api/v1/hdc?identificacion=%s&primerApellido=%s&tipoIdentificacion=%s",
                        identifiction, primerApellido, tipoIdentificacion));

        Param param0 = paramRepository.findById(0L)
                .orElseThrow(() -> new IllegalStateException(String.format("Param with id %s not found", 0L)));
        urlBuilder.append(String.format("&urlDatacredito=%s", param0.getValue()));

        Param param1 = paramRepository.findById(1L)
                .orElseThrow(() -> new IllegalStateException(String.format("Param with id %s not found", 0L)));
        urlBuilder.append(String.format("&usuarioOkta=%s", param1.getValue()));

        Param param2 = paramRepository.findById(2L)
                .orElseThrow(() -> new IllegalStateException(String.format("Param with id %s not found", 0L)));
        urlBuilder.append(String.format("&claveOkta=%s", param2.getValue()));

        Param param3 = paramRepository.findById(3L)
                .orElseThrow(() -> new IllegalStateException(String.format("Param with id %s not found", 0L)));
        urlBuilder.append(String.format("&usuario=%s", param3.getValue()));

        Param param4 = paramRepository.findById(4L)
                .orElseThrow(() -> new IllegalStateException(String.format("Param with id %s not found", 0L)));
        urlBuilder.append(String.format("&claveCorta=%s", param4.getValue()));

        OkHttpClient client = new OkHttpClient().newBuilder().build();
        String URL = urlBuilder.toString();
        log.info("Call to Python rest API [{}]", URL);
        Request request = new Request.Builder()
                .url(URL)
                .method("GET", null)
                .build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response.body().string();
    }

}
