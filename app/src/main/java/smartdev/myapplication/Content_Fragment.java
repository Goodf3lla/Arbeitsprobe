package smartdev.myapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.gson.Gson;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Andi on 08.07.2016.
 */
public class Content_Fragment extends Fragment {

    private String title;
    private int page;
    MotiusData[] motiusData;
    RecyclerView mRecyclerView;

    // newInstance constructor for creating fragment with arguments
    public static Content_Fragment newInstance(int page, String title) {
        Content_Fragment fragmentFirst = new Content_Fragment();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someTitle", title);
        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }

    // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = getArguments().getInt("someInt", 1);
        title = getArguments().getString("Content");
    }

    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.content_fragment, container, false);
        final OkHttpClient client = new OkHttpClient();
        final Gson gson = new Gson();
        mRecyclerView=(RecyclerView) view.findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager= new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        Request request = new Request.Builder().url("https://www.motius.de/api/usecases/").build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }
            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                motiusData=gson.fromJson(response.body().charStream(),MotiusData[].class);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        DataAdapter da=new DataAdapter(motiusData);
                        mRecyclerView.setAdapter(da);
                    }
                });
            }
        });
        return view;
    }

    public static class MotiusData{

        String title;

        String body;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }

    }
}

