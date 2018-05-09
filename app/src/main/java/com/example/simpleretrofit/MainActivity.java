package com.example.simpleretrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.simpleretrofit.adapter.AnswerAdapter;
import com.example.simpleretrofit.data.data.model.Item;
import com.example.simpleretrofit.data.data.model.SOAnswersResponse;
import com.example.simpleretrofit.data.data.remote.ApiUtils;
import com.example.simpleretrofit.data.data.remote.SOService;

import java.util.ArrayList;

import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Callback;

public class MainActivity extends AppCompatActivity {
    private AnswerAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private SOService mService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mService = ApiUtils.getSOService();
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_answers);
        mAdapter = new AnswerAdapter(this, new ArrayList<Item>(0), new AnswerAdapter.PostItemListener() {
            @Override
            public void onPostClick(long id) {
                Toast.makeText(MainActivity.this, "Post id is: " + id, Toast.LENGTH_SHORT).show();
            }
        });

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        mRecyclerView.addItemDecoration(itemDecoration);

        loadAnswers();
    }

    public void loadAnswers() {
        mService.getAnswers().enqueue(new Callback<SOAnswersResponse>() {
            @Override
            public void onResponse(Call<SOAnswersResponse> call, retrofit2.Response<SOAnswersResponse> response) {
                if (response.isSuccessful())
                {
                    mAdapter.updateAnswers(response.body().getItems());

                }




            }

            @Override
            public void onFailure(Call<SOAnswersResponse> call, Throwable t) {

                showErrorMessage();

            }






      

        });
    }
    
    public void showErrorMessage(){
        Toast.makeText(this, "Error loading posts", Toast.LENGTH_SHORT).show();
    }
}
