package com.androidalgorithms.rbrauwers.android_algorithms;

import android.content.DialogInterface;
import android.os.Build;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment implements View.OnClickListener {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";
        private int index;

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            index = getArguments().getInt(ARG_SECTION_NUMBER);
            Button button = rootView.findViewById(R.id.button);
            button.setOnClickListener(this);

            if (index == 0) {
                button.setText("BALANCED STRING");
            }
            else if (index == 1) {
                button.setText("ALMOST PALINDROME");
            }
            else if (index == 2) {
                button.setText("IS BINARY TREE");
            }

            return rootView;
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.button:
                    if (index == 0) {
                        balanceStrings();
                    }
                    else if (index == 1) {
                        String src = "abcxba";
                        showAlert(String.format(Locale.getDefault(),"Word: %s IsAlmostPalindrome: %b", src, Algorithms.isAlmostPalindrome(src)));
                    }
                    else if (index == 2) {
                        binaryTree();
                    }
                    break;
            }
        }

        private void balanceStrings() {
            String[] expressions = new String[] {
                    "<<>>", "<><>", "<<<>>>", "<>>", "><<", "<>><", "<>><>"
            };

            int[] maxReplacements = new int[] {0, 0, 0, 0, 0, 0, 0};
            int[] result = Algorithms.balancedOrNot(expressions, maxReplacements);

            StringBuilder sb = new StringBuilder();
            for (int i=0; i<expressions.length; i++) {
                sb.append(String.format(Locale.getDefault(),"%s Max rep.: %d Can balance: %b %s", expressions[i], maxReplacements[i], result[i] == 1, Character.LINE_SEPARATOR));
            }

            showAlert(sb.toString());
        }

        private void binaryTree() {
            boolean one = false, two = false, three = false;

            Node node0_1 = new Node(1);
            Node node0_2 = new Node(6);
            Node node0_3 = new Node(4);
            Node node0_4 = new Node(9);
            Node node0_5 = new Node(3, node0_1, node0_2);
            Node node0_6 = new Node(7, node0_3, node0_4);
            Node node0_7 = new Node(5, node0_5, node0_6);

            //Algorithms.validateIsLessThanRootValue(node0_7.left, node0_7);
            //Algorithms.validateIsGreaterThanRootValue(node0_7.right, node0_7);

            Node node1_1 = new Node(1);
            Node node1_2 = new Node(4);
            Node node1_3 = new Node(7);
            Node node1_4 = new Node(15);
            Node node1_5 = new Node(3, node1_1, node1_2);
            Node node1_6 = new Node(10, node1_3, node1_4);
            Node node1_7 = new Node(5, node1_5, node1_6);

            //Algorithms.validateIsLessThanRootValue(node1_7.left, node1_7);
            //Algorithms.validateIsGreaterThanRootValue(node1_7.right, node1_7);

            Node node2_1 = new Node(10);
            Node node2_2 = new Node(15);
            Node node2_3 = new Node(90);
            Node node2_4 = new Node(120);
            Node node2_5 = new Node(30, node2_1, node2_2);
            Node node2_6 = new Node(100, node2_3, node2_4);
            Node node2_7 = new Node(50, node2_5, node2_6);

            //one = Algorithms.validateIsLessThanRootValue(node2_7.left, node2_7);
            //two = Algorithms.validateIsGreaterThanRootValue(node2_7.right, node2_7);
            //three = Algorithms.validateWithParent(node2_7, null);

            Node node3_1 = new Node(1);
            Node node3_2 = new Node(3);
            Node node3_3 = new Node(5);
            Node node3_4 = new Node(7);
            Node node3_5 = new Node(9);
            Node node3_6 = new Node(11);
            Node node3_7 = new Node(13); // Correct: 13 Exercise: 12
            Node node3_8 = new Node(15);
            Node node3_9 = new Node(2, node3_1, node3_2);
            Node node3_10 = new Node(6, node3_3, node3_4);
            Node node3_11 = new Node(10, node3_5, node3_6);
            Node node3_12 = new Node(14, node3_7, node3_8);
            Node node3_13 = new Node(4, node3_9, node3_10);
            Node node3_14 = new Node(12, node3_11, node3_12); // Correct: 12 Exercise: 13
            Node node3_15 = new Node(8, node3_13, node3_14);

            one = Algorithms.validateIsLessThanRootValue(node3_15.left, node3_15);
            two = Algorithms.validateIsGreaterThanRootValue(node3_15.right, node3_15);
            //three = Algorithms.validateWithParent(node3_15, null);

            CommonUtils.log(String.format(Locale.getDefault(), "One: %b Two: %b Three: %b", one, two, three));
        }

        private void showAlert(String msg) {
            AlertDialog.Builder builder;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                builder = new AlertDialog.Builder(getActivity(), android.R.style.Theme_Material_Dialog_Alert);
            } else {
                builder = new AlertDialog.Builder(getActivity());
            }
            builder.setMessage(msg)
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    })
                    .show();
        }
    }

    /*

     */

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }
    }
}
