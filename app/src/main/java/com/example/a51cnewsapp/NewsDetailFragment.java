package com.example.a51cnewsapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class NewsDetailFragment extends Fragment {

    private static final String ARG_TITLE = "title";
    private static final String ARG_CONTENT = "content";
    private static final String ARG_IMAGE_RESOURCE = "imageResource";

    private String mTitle;
    private String mContent;
    private int mImageResource;

    public NewsDetailFragment() {
        // Required empty public constructor
    }

    // 使用静态方法创建新的 NewsDetailFragment 实例，并传入新闻标题和内容
    public static NewsDetailFragment newInstance(int imageResource,String title, String content) {
        NewsDetailFragment fragment = new NewsDetailFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_IMAGE_RESOURCE, imageResource);
        args.putString(ARG_TITLE, title);
        args.putString(ARG_CONTENT, content);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mImageResource = getArguments().getInt(ARG_IMAGE_RESOURCE);
            mTitle = getArguments().getString(ARG_TITLE);
            mContent = getArguments().getString(ARG_CONTENT);

        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_news_detail, container, false);

        // 获取布局中的 TextView，并设置新闻标题和内容
        ImageView imageView = view.findViewById(R.id.image_news);
        TextView titleTextView = view.findViewById(R.id.text_news_title);
        TextView contentTextView = view.findViewById(R.id.text_news_content);

        if (mTitle != null && mContent != null) {
            imageView.setImageResource(mImageResource);
            titleTextView.setText(mTitle);
            contentTextView.setText(mContent);
        }
        // 获取返回按钮，并添加点击事件
        Button backButton = view.findViewById(R.id.button_back);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 返回到主页面
                getActivity().onBackPressed();
            }
        });

        // 设置相关新闻 RecyclerView
        RecyclerView recyclerView = view.findViewById(R.id.recycler_related_news);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new RelatedNewsAdapter(getRelatedNews()));


        return view;
    }

    // 模拟一些相关新闻数据
    private List<RelatedNewsItem> getRelatedNews() {
        List<RelatedNewsItem> relatedNews = new ArrayList<>();
        relatedNews.add(new RelatedNewsItem(R.drawable.news_image,"Related News 1", "Description of Related News 1"));
        relatedNews.add(new RelatedNewsItem(R.drawable.news_image,"Related News 2", "Description of Related News 2"));
        relatedNews.add(new RelatedNewsItem(R.drawable.news_image,"Related News 3", "Description of Related News 3"));
        return relatedNews;
    }

    // 定义相关新闻数据项
    private static class RelatedNewsItem {
        private int imageResource;
        private String title;
        private String description;


        RelatedNewsItem(int imageResource,String title, String description) {
            this.imageResource = imageResource;
            this.title = title;
            this.description = description;
        }
    }

    // RecyclerView 适配器
    private class RelatedNewsAdapter extends RecyclerView.Adapter<RelatedNewsAdapter.ViewHolder> {
        private List<RelatedNewsItem> relatedNewsList;

        RelatedNewsAdapter(List<RelatedNewsItem> relatedNewsList) {
            this.relatedNewsList = relatedNewsList;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_related_news, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            RelatedNewsItem relatedNewsItem = relatedNewsList.get(position);

            holder.titleTextView.setText(relatedNewsItem.title);
            holder.descriptionTextView.setText(relatedNewsItem.description);
        }

        @Override
        public int getItemCount() {
            return relatedNewsList.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            ImageView newsImageView;
            TextView titleTextView;
            TextView descriptionTextView;


            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                newsImageView = itemView.findViewById(R.id.image_related_news);
                titleTextView = itemView.findViewById(R.id.text_related_news_name);
                descriptionTextView = itemView.findViewById(R.id.text_related_news_description);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    RelatedNewsItem currentItem = relatedNewsList.get(getAdapterPosition());
                    // 打开相关新闻详情页面
                    openRelatedNewsDetail(currentItem.imageResource,currentItem.title, currentItem.description);
                }
            });
            }
        }


    }

    private void openRelatedNewsDetail(int imageResource, String title, String description) {
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, NewsDetailFragment.newInstance(imageResource,title, description));
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

}
