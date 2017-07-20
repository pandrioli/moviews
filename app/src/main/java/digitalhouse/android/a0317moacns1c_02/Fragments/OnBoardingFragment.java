package digitalhouse.android.a0317moacns1c_02.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import digitalhouse.android.a0317moacns1c_02.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OnBoardingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OnBoardingFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PAGE = "page";

    private Integer mPage;

    private List<String> messages;


    public OnBoardingFragment() {
        // Required empty public constructor
    }


    public static OnBoardingFragment newInstance(Integer page) {
        OnBoardingFragment fragment = new OnBoardingFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mPage = getArguments().getInt(ARG_PAGE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_on_boarding, container, false);
        setupMessages();
        TextView textViewMessage = (TextView)view.findViewById(R.id.textViewOnBoardingMessage);
        textViewMessage.setText(messages.get(mPage));
        return view;
    }

    private void setupMessages() {
        messages = new ArrayList<>();
        messages.add("Hi and welcome!\r\n\r\nWe will give you access to a huge database of movies and TV shows.\r\n\r\nLet's take a quick view at the different sections we have...");
        messages.add("In the main section you can easily navigate through popular, latest and upcoming movies and series.\r\n\r\nClick on any to get full details.");
        messages.add("Find the top rated movies and series of all time.\r\n\r\nSet a release year range and filter the results by genres.");
        messages.add("Looking for a specific movie or TV show?\r\n\r\nJust enter some keywords and we will find it for you!\r\n\r\nYou can also search for people like actors and directors.");
        messages.add("Here you will find the movies and series you saved for fast later access.");
        messages.add("Manage your account and login options.\r\n\r\nBrowse your favorites movies and series.");
        messages.add("Thanks for installing our app!\r\n\r\nHope you enjoy it!");
    }

}
