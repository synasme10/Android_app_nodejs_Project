package HirerFragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.e.contracterandworkerfinder.R;

import java.util.List;

import Adapter.WorkerAdapter;
import DayJobApi.Api;
import Url.BaseUrl;
import model.EmployeeModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HirerDashboardFragment extends Fragment {

    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_worker_dashboard,container,false);


        recyclerView=view.findViewById(R.id.recyclerView);

        getAllEmployee();

        return view;
    }


    private void getAllEmployee() {

        Api api= BaseUrl.getInstance().create(Api.class);

        final Call<List<EmployeeModel>> employeeList=api.getAllEmployee();

        employeeList.enqueue(new Callback<List<EmployeeModel>>() {
            @Override
            public void onResponse(Call<List<EmployeeModel>> call, Response<List<EmployeeModel>> response) {
                if(! response.isSuccessful()){
                    Toast.makeText(getContext(), "Code"+response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }

                List<EmployeeModel> employeeModelList=response.body();

                WorkerAdapter workerAdapter=new WorkerAdapter(getContext(),employeeModelList);
                recyclerView.setAdapter(workerAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

            }

            @Override
            public void onFailure(Call<List<EmployeeModel>> call, Throwable t) {
                Toast.makeText(getContext(), "Error:"+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}
