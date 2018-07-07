package com.example.abc.reimbursement;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.TextView;

import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.text.Element;
import com.google.android.gms.vision.text.Line;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ImageToTextConverter extends AppCompatActivity {

    TextView mTextView;

    com.google.android.gms.vision.CameraSource mCameraSource;

    SurfaceView mCameraView;
    String word2;

    private static final String TAG = "MainActivity";
    private static final int requestPermissionID = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_to_text_converter);

        setTitle("Scan");

        Intent intent =  getIntent();

        mCameraView = (SurfaceView) findViewById(R.id.surface_view);
        mTextView = (TextView) findViewById(R.id.text_view);

        startCameraSource();
    }


    private void startCameraSource() {

        //Create the TextRecognizer
        final TextRecognizer textRecognizer = new TextRecognizer.Builder(getApplicationContext()).build();

        if (!textRecognizer.isOperational()) {
            Log.w(TAG, "Detector dependencies not loaded yet");
        } else {

            //Initialize camerasource to use high resolution and set Autofocus on.
            mCameraSource = new com.google.android.gms.vision.CameraSource.Builder(getApplicationContext(), textRecognizer)
                    .setFacing(com.google.android.gms.vision.CameraSource.CAMERA_FACING_BACK)
                    .setRequestedPreviewSize(1280, 1024)
                    .setAutoFocusEnabled(true)
                    .setRequestedFps(2.0f)
                    .build();

            /**
             * Add call back to SurfaceView and check if camera permission is granted.
             * If permission is granted we can start our cameraSource and pass it to surfaceView
             */
            mCameraView.getHolder().addCallback(new SurfaceHolder.Callback() {
                @Override
                public void surfaceCreated(SurfaceHolder holder) {
                    try {

                        if (ActivityCompat.checkSelfPermission(getApplicationContext(),
                                Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {

                            ActivityCompat.requestPermissions(ImageToTextConverter.this,
                                    new String[]{Manifest.permission.CAMERA},
                                    requestPermissionID);
                            return;
                        }
                        mCameraSource.start(mCameraView.getHolder());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
                }

                /**
                 * Release resources for cameraSource
                 */
                @Override
                public void surfaceDestroyed(SurfaceHolder holder) {
                    mCameraSource.stop();
                }
            });

            //Set the TextRecognizer's Processor.
            textRecognizer.setProcessor(new Detector.Processor<TextBlock>() {
                @Override
                public void release() {
                }

                /**
                 * Detect all the text from camera using TextBlock and the values into a stringBuilder
                 * which will then be set to the textView.
                 * */

                @Override
                public void receiveDetections(Detector.Detections<TextBlock> detections) {
                    final SparseArray<TextBlock> items = detections.getDetectedItems();

                    if (items.size() != 0) {

                        mTextView.post(new Runnable() {
                            @Override
                            public void run() {

                                double ttt = Double.parseDouble(getAmount(items));

                                //mCameraSource.stop();

                                Intent resultIntent = new Intent();
                                resultIntent.putExtra("result" , ttt);
                                setResult(RESULT_OK , resultIntent);
                                finish();
                                //mCameraSource.stop();

                            }
                        });
                    }
                }
            });
        }
    }



    public String getAmount(SparseArray<TextBlock> items) {

        ArrayList<Double> allAmountList = new ArrayList<Double>();

        for (int i = 0; i < items.size(); i++) {


            TextBlock item = items.valueAt(i);

            List<Line> lines = (List<Line>) item.getComponents();

            //int j = 0;

            for (Line line : lines) {

                List<Element> elements = (List<Element>) line.getComponents();
                //String prev = null;
                //String required = null;
                //String additional = "";

                //int k = 0;

                for (Element element : elements) {
                    String word = element.getValue();

                    if (word.equalsIgnoreCase("rupees")) {
                        //ArrayList<String> numberInWords = new ArrayList<>();
                        StringBuilder noToWord = new StringBuilder();

                        String stringForConvertion, convertedString;

                        for (int l = 1; l < elements.size(); l++) {

                            if (!(elements.get(l).getValue().toString().contains("only") || elements.get(l).getValue().toString().contains("Only") || elements.get(l).getValue().toString().contains("ONLY"))) {
                                //numberInWords.add(elements.get(l).getValue().toString());
                                //noToWord.append(numberInWords.get(l-1));
                                noToWord.append(elements.get(l).getValue().toString().toLowerCase());
                                noToWord.append(" ");

                            }
                            Log.i("tag", elements.get(l).getValue().toString());
                            //mTextView.setText(numberInWords.get(0));
                        }

                        stringForConvertion = noToWord.toString();
                        //convertedString = replaceNumbers(stringForConvertion);
                        int num = inNumerals(stringForConvertion);
                        convertedString = num + "";


                        //mTextView.setText("Final Amount = " + convertedString.replaceFirst("^0+(?!$)", ""));

                        mTextView.setText("Final Amount = " + convertedString);

                        mCameraSource.stop();
                        return convertedString;

                    }

                    Pattern p = Pattern.compile("[0-9]?[,]?[0-9]?[0-9]?[,]?[0-9]?[0-9]?[0-9]?[.][0-9][0-9]");
                    Matcher m = p.matcher(word);

                    if (m.find()) {

                        Double allAmount;
                        if (!(word.indexOf(',') == -1)) {
                            allAmount = Double.parseDouble(word.replaceAll("[,]", ""));
                        }

                        //int allAmount = Integer.parseInt(word.replaceAll("[,]", ""));

                        else {
                            allAmount = Double.parseDouble(word);
                        }

                        allAmountList.add(allAmount);
                        //allAmountList.add(word.replaceAll("[,]", ""));
                    }

                }

            }

        }

        if (!(allAmountList.isEmpty())) {

            Double max = max(allAmountList);

            mCameraSource.stop();
            mTextView.setText("Final Amount = " + max.toString());
            return max.toString();
        }


        mCameraSource.stop();
        return "00";


    }

    public Double max(ArrayList<Double> maxArray) {
        double highest = maxArray.get(0);

        for (int s = 0; s < maxArray.size(); s++) {
            if (maxArray.get(s) > highest)
                highest = maxArray.get(s);

        }
        return highest;
    }


    public int inNumerals(String inwords) {
        int wordnum = 0;
        String[] arrinwords = inwords.split(" ");
        int arrinwordsLength = arrinwords.length;
        if (inwords.equals("zero")) {
            return 0;
        }
        if (inwords.contains("thousand")) {
            int indexofthousand = inwords.indexOf("thousand");
            //System.out.println(indexofthousand);
            String beforethousand = inwords.substring(0, indexofthousand);
            //System.out.println(beforethousand);
            String[] arrbeforethousand = beforethousand.split(" ");
            int arrbeforethousandLength = arrbeforethousand.length;
            //System.out.println(arrbeforethousandLength);
            if (arrbeforethousandLength == 2) {
                wordnum = wordnum + 1000 * (wordtonum(arrbeforethousand[0]) + wordtonum(arrbeforethousand[1]));
                //System.out.println(wordnum);
            }
            if (arrbeforethousandLength == 1) {
                wordnum = wordnum + 1000 * (wordtonum(arrbeforethousand[0]));
                //System.out.println(wordnum);
            }

        }
        if (inwords.contains("hundred")) {
            int indexofhundred = inwords.indexOf("hundred");
            //System.out.println(indexofhundred);
            String beforehundred = inwords.substring(0, indexofhundred);

            //System.out.println(beforehundred);
            String[] arrbeforehundred = beforehundred.split(" ");
            int arrbeforehundredLength = arrbeforehundred.length;
            wordnum = wordnum + 100 * (wordtonum(arrbeforehundred[arrbeforehundredLength - 1]));
            String afterhundred = inwords.substring(indexofhundred + 8);//7 for 7 char of hundred and 1 space
            //System.out.println(afterhundred);
            String[] arrafterhundred = afterhundred.split(" ");
            int arrafterhundredLength = arrafterhundred.length;
            if (arrafterhundredLength == 1) {
                wordnum = wordnum + (wordtonum(arrafterhundred[0]));
            }
            if (arrafterhundredLength == 2) {
                wordnum = wordnum + (wordtonum(arrafterhundred[1]) + wordtonum(arrafterhundred[0]));
            }
            //System.out.println(wordnum);

        }
        if (!inwords.contains("thousand") && !inwords.contains("hundred")) {
            if (arrinwordsLength == 1) {
                wordnum = wordnum + (wordtonum(arrinwords[0]));
            }
            if (arrinwordsLength == 2) {
                wordnum = wordnum + (wordtonum(arrinwords[1]) + wordtonum(arrinwords[0]));
            }
            //System.out.println(wordnum);
        }


        return wordnum;
    }


    public int wordtonum(String word) {
        int num = 0;
        switch (word) {
            case "one":
                num = 1;
                break;
            case "two":
                num = 2;
                break;
            case "three":
                num = 3;
                break;
            case "four":
                num = 4;
                break;
            case "five":
                num = 5;
                break;
            case "six":
                num = 6;
                break;
            case "seven":
                num = 7;
                break;
            case "eight":
                num = 8;
                break;
            case "nine":
                num = 9;
                break;
            case "ten":
                num = 10;
                break;
            case "eleven":
                num = 11;
                break;
            case "twelve":
                num = 12;
                break;
            case "thirteen":
                num = 13;
                break;
            case "fourteen":
                num = 14;
                break;
            case "fifteen":
                num = 15;
                break;
            case "sixteen":
                num = 16;
                break;
            case "seventeen":
                num = 17;
                break;
            case "eighteen":
                num = 18;
                break;
            case "nineteen":
                num = 19;
                break;
            case "twenty":
                num = 20;
                break;
            case "thirty":
                num = 30;
                break;
            case "forty":
                num = 40;
                break;
            case "fifty":
                num = 50;
                break;
            case "sixty":
                num = 60;
                break;
            case "seventy":
                num = 70;
                break;
            case "eighty":
                num = 80;
                break;
            case "ninety":
                num = 90;
                break;
            case "hundred":
                num = 100;
                break;
            case "thousand":
                num = 1000;
                break;
           /*default: num = "Invalid month";
                             break;*/
        }
        return num;
    }

}

