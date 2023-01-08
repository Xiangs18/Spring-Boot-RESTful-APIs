package com.example.springapi.service;

import com.example.springapi.api.model.Api;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@Service
public class ApiService {
    private final List<Api> apiList;

    public ApiService() {
        apiList = new ArrayList<>();

        Api api1 = new Api(1, "a1");
        Api api2 = new Api(2, "a2");

        apiList.addAll(Arrays.asList(api2, api1));
        apiList.sort(Comparator.comparingInt(Api::getId));

    }

    public List<Api> listAllApis() {
        return apiList;
    }

    public Api addApi() {
        int id = apiList.get(apiList.size() - 1).getId() + 1;
        Api newApi = new Api(id, "a" + id);
        apiList.add(newApi);
        return newApi;
    }

    public void deleteApi(int id) {
        apiList.removeIf(api -> id == api.getId());
    }

    public void updateApi(int id, String name) {
        for (Api api: apiList) {
            if (id == api.getId()) {
                api.setName(name);
            }
        }
    }
}
