package com.devapp.myapplication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.devapp.myapplication.adapter.InterestLikeAdapter;
import com.devapp.myapplication.model.InterestLikeModel;

import java.util.ArrayList;

/**
 * Created by SARIN on 6/3/2017.
 */

public class ProfilePageFragment extends Fragment implements View.OnClickListener {

    CollapsingToolbarLayout collapsingToolbarLayout;
    ImageView imageView_add_interest;
    PopupWindow popupWindow;
    String[] availableInterests = {"Electronics", "Fashion", "Grooming", "Furniture"};
    RecyclerView recyclerView_interests;
    RecyclerView recyclerView_likes;
    InterestLikeAdapter adapter;
    ArrayList<InterestLikeModel> interestLikeModels;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.profile_page_layout, container, false);
        collapsingToolbarLayout = (CollapsingToolbarLayout) v.findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle("Prashant Sarin");
        toolbarTextAppearance();
        imageView_add_interest = (ImageView) v.findViewById(R.id.interest_add_img);
        imageView_add_interest.setOnClickListener(this);
        recyclerView_interests=(RecyclerView)v.findViewById(R.id.recycler_interest);
        recyclerView_likes=(RecyclerView)v.findViewById(R.id.recycler_likes);
        interestLikeModels=new ArrayList<>();

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

//    private void dynamicToolbarColor() {
//
//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
//                R.drawable.);
//        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
//
//            @Override
//            public void onGenerated(Palette palette) {
//                collapsingToolbarLayout.setContentScrimColor(palette.getMutedColor(R.attr.colorPrimary));
//                collapsingToolbarLayout.setStatusBarScrimColor(palette.getMutedColor(R.attr.colorPrimaryDark);
//            }
//        });
//    }

    private void toolbarTextAppearance() {
        collapsingToolbarLayout.setExpandedTitleColor(0xff00ffff);
        collapsingToolbarLayout.setCollapsedTitleTextColor(0xffffffff);
    }

    public int[] getWindowParam() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;
        int[] param = {height, width};
        return param;
    }

    public void createPopup() {
        View selectInterestView = LayoutInflater.from(getContext()).inflate(R.layout.list_interest, null, false);
        int[] param = getWindowParam();
        int height = param[0];
        int width = param[1];
        ListView listView = (ListView) selectInterestView.findViewById(R.id.listview_interest);
        ArrayAdapter arrayAdapter=new ArrayAdapter(getContext(),android.R.layout.simple_list_item_1,availableInterests);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Object selectedItem= adapterView.getItemAtPosition(i);
                InterestLikeModel model=new InterestLikeModel();
                model.setImg(R.drawable.ic_latop);
                interestLikeModels.add(model);
                updateInterestLike(recyclerView_interests);
                String selectedItemName=selectedItem.toString();
                Toast.makeText(getContext(),selectedItemName+"was selected",Toast.LENGTH_SHORT).show();
            }
        });
        //creating popup window for asking interests
        popupWindow = new PopupWindow(selectInterestView, width - 200, height - 440, true);
        popupWindow.showAtLocation(getView(), Gravity.CENTER, 0, 0);
        TextView t = (TextView) selectInterestView.findViewById(R.id.text_popup);
        t.setOnClickListener(this);
    }

    public void updateInterestLike(RecyclerView recyclerView){
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        adapter=new InterestLikeAdapter(interestLikeModels,getContext());
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.interest_add_img:
                Toast.makeText(getContext(), "add interest clicked", Toast.LENGTH_SHORT).show();
                createPopup();
                break;
            case R.id.text_popup:
                Toast.makeText(getContext(), "text was clicked ", Toast.LENGTH_SHORT).show();
                break;
        }

    }
}
