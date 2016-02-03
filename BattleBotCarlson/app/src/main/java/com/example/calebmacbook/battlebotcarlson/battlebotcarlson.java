package com.example.calebmacbook.battlebotcarlson;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Random;
import android.app.AlertDialog;
import java.util.concurrent.TimeUnit;
import android.os.Handler;
import java.util.logging.LogRecord;

public class battlebotcarlson extends AppCompatActivity {
    public static Socket socket;
    public static BufferedWriter out = null;
    public static BufferedReader in = null;
    public String messageOut, IPaddress;
    public int  dimenX, dimenY, currentX, currentY, moveDirection = -1, savedAngle;
    public Random r = new Random();
    public boolean moveBool = true, shootBool =true, wantToShoot = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        savedAngle = r.nextInt(360);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battlebotcarlson);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Button fup = (Button) findViewById(R.id.fup);
        fup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fire(0);
            }
        });
        Button frdu = (Button) findViewById(R.id.frdu);
        frdu.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                fire(45);
            }
        });
        Button fright = (Button) findViewById(R.id.fright);
        fright.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                fire(90);
            }
        });
        Button frdd = (Button) findViewById(R.id.frdd);
        frdd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                fire(135);
            }
        });
        Button fdown = (Button) findViewById(R.id.fdown);
        fdown.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                fire(180);
            }
        });
        Button fldd = (Button) findViewById(R.id.fldd);
        fldd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                fire(225);
            }
        });
        Button fleft = (Button) findViewById(R.id.fleft);
        fleft.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                fire(270);
            }
        });
        Button fldu = (Button) findViewById(R.id.fldu);
        fldu.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                fire(315);
            }
        });
        Button up = (Button) findViewById(R.id.up);
        /*up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*if(moveBool == true){
                    move(0,-1);
                    moveBool = false;
                }
                moveDirection = 2;
                //up.performClick();
                move(0,-1);
            }
        });*/

        up.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        moveDirection = 2;
                        //up.performClick();
                        move(0,-1);
                        break;
                    case MotionEvent.ACTION_UP:
                        moveDirection = -1;
                        break;
                }
                return false;
            }
        });
        Button ldu = (Button) findViewById(R.id.ldu);
        ldu.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        moveDirection = 1;
                        //up.performClick();
                        move(-1, -1);
                        break;
                    case MotionEvent.ACTION_UP:
                        moveDirection = -1;
                        break;
                }
                return false;
            }
        });
        Button ldd = (Button) findViewById(R.id.ldd);
        ldd.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        moveDirection = 6;
                        //up.performClick();
                        move(-1, 1);
                        break;
                    case MotionEvent.ACTION_UP:
                        moveDirection = -1;
                        break;
                }
                return false;
            }
        });
        Button left = (Button) findViewById(R.id.left);
        left.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        moveDirection = 4;
                        //up.performClick();
                        move(-1, 0);
                        break;
                    case MotionEvent.ACTION_UP:
                        moveDirection = -1;
                        break;
                }
                return false;
            }
        });
        Button right = (Button) findViewById(R.id.right);
        right.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        moveDirection = 5;
                        //up.performClick();
                        move(1, 0);
                        break;
                    case MotionEvent.ACTION_UP:
                        moveDirection = -1;
                        break;
                }
                return false;
            }
        });
        Button rdu = (Button) findViewById(R.id.rdu);
        rdu.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        moveDirection = 3;
                        //up.performClick();
                        move(1, -1);
                        break;
                    case MotionEvent.ACTION_UP:
                        moveDirection = -1;
                        break;
                }
                return false;
            }
        });
        Button rdd = (Button) findViewById(R.id.rdd);
        rdd.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        moveDirection = 8;
                        //up.performClick();
                        move(1, 1);
                        break;
                    case MotionEvent.ACTION_UP:
                        moveDirection = -1;
                        break;
                }
                return false;
            }
        });
        Button down = (Button) findViewById(R.id.down);
        down.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        moveDirection = 7;
                        //up.performClick();
                        move(0, 1);
                        break;
                    case MotionEvent.ACTION_UP:
                        moveDirection = -1;
                        break;
                }
                return false;
            }
        });
        Button km = (Button) findViewById(R.id.ai);
        km.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Thread kobi = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        kobayashiMaru();
                    }
                });
                kobi.start();
            }
        });
        Button scan = (Button) findViewById(R.id.scan);
        scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wantToShoot = true;
                try{
                    out.write("scan", 0, 4);
                    out.newLine();
                    out.flush();
                }catch (IOException e) {
                    Log.i("Error", "Didn't scan");
                }
            }
        });
        Button stop = (Button) findViewById(R.id.stop);
        stop.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                wantToShoot = false;
            }
        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_battlebotcarlson, menu);
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
            getIP();
            return true;
        }
        else if(id == R.id.action_disconnect)
        {
            Log.i("GameOver", "Exiting the arena");
            try{
                out.close();
                in.close();
                socket.close();}
            catch(IOException e)
            {

            }
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void getIP(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("IP Address");
        final EditText text = new EditText(this);
        text.setInputType(3);
        builder.setView(text);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                IPaddress = text.getText().toString();
                new Thread(new connectNetwork()).start();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();

    }
    public void move(int d1, int d2) {
        messageOut = "move "+d1+" " + d2;
                try {
                    out.write(messageOut, 0, messageOut.length());
                    out.newLine();
                    out.flush();
                } catch (IOException e) {
                    Log.i("Error", "Did not move");
                }
    }
    public void fire(int angle)
    {
        wantToShoot = true;
        savedAngle = angle;
        messageOut = "fire "+ angle;
        try{
            out.write(messageOut, 0, messageOut.length());
            out.newLine();
            out.flush();
        }catch(IOException e){
            Log.i("Error", "Didn't fire");
        }
    }
    public void kobayashiMaru(){
        int halfx, halfy, moveX, moveY, destX, destY;
        halfx = dimenX/2;
        halfy = dimenY/2;
        if(currentX <= halfx) {
            destX = 0;
            if(currentY <= halfy) {
                destY = 0;
            }
            else {
                destY = dimenY - 10;
            }
        }
        else {
            destX = dimenX - 10;
            if(currentY <= halfy) {
                destY = 0;
            }
            else {
                destY = dimenY - 10;
            }
        }


        while (currentX != destX || currentY != destY)
        {
            if(currentX < destX) {
                moveX = 1;
            }
            else if(currentX > destX) {
                moveX = -1;
            }
            else {
                moveX = 0;
            }
            if(currentY < destY) {
                moveY = 1;
            }
            else if(currentY > destY) {
                moveY = -1;
            }
            else {
                moveY = 0;
            }
            if(moveBool == true){
                move(moveX,moveY);
                moveBool = false;
            }
        }

    }
    class connectNetwork implements Runnable{
        public void run(){
            try {
                String hostname = IPaddress; // remote machine
                String messageBack ="Destruct-o-bot 0 0 4";
                TextView message = (TextView) findViewById(R.id.message);
                String test;
                int port = 3012;  //remote port number

                //make the connection
                InetAddress serverAddr = InetAddress.getByName(IPaddress);
                socket = new Socket(serverAddr, port);
                out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String[] setupInfo = in.readLine().split(" ");
                Log.i("Setup", setupInfo[0] + setupInfo[1] + setupInfo[2]);
                dimenX = Integer.parseInt(setupInfo[2]);
                dimenY = Integer.parseInt(setupInfo[3]);
                Log.i("DIMENSIONS", dimenX + " " + dimenY);
                //message.setText(test);
                //Log.i("Test",in.readLine());
                out.write(messageBack, 0, messageBack.length());
                out.newLine();
                out.flush();
                Log.i("Setup", in.readLine());
                //while (true) {
                //Log.i("Setup", in.readLine());

                String[] status = in.readLine().split(" ");
                out.write("noop", 0, 4);
                out.newLine();
                out.flush();
                int spread = 0;
                while(true){
                    status = in.readLine().split(" ");
                    Log.i("Status", status[0]);

                    if (status[0].equals("Status")){
                        Log.i("Position", status[1] + " " + status[2]);
                        int moveAbility = Integer.parseInt(status[3]);
                        int shootAbility = Integer.parseInt(status[4]);
                        currentX = Integer.parseInt(status[1]);
                        currentY = Integer.parseInt(status[2]);
                        if (shootAbility == 0)
                            shootBool = true;
                        if(moveAbility < 0)
                        {
                            out.write("noop", 0, 4);
                            out.newLine();
                            out.flush();
                        }
                        else if(shootAbility == 0 && wantToShoot == true)
                        {
                            if(spread%3 == 0) {
                                fire(savedAngle - 5);
                                savedAngle+=5;
                            }
                            else if(spread%3 == 1)
                                fire(savedAngle);
                            else{
                                fire(savedAngle+5);
                                savedAngle-=5;
                            }
                            spread++;

                        }
                        else if(moveDirection == 2){
                                move(0,-1);
                        }
                        else if(moveDirection == 1){
                            move(-1,-1);
                        }
                        else if(moveDirection == 3){
                            move(1,-1);
                        }
                        else if(moveDirection == 4){
                            move(-1,0);
                        }
                        else if(moveDirection == 5){
                            move(1,0);
                        }
                        else if(moveDirection == 6){
                            move(-1, 1);
                        }
                        else if(moveDirection == 7){
                            move(0, 1);
                        }
                        else if(moveDirection == 8){
                            move(1, 1);
                        }
                        else
                            moveBool = true;
                    }
                    else if(status[0].equals("scan") && status[1].equals("bot")){
                        Log.i("BOTFOUND", "I FOUND A BAD GUY!!!!!!!*********************");
                        int botx, boty;
                        botx = Integer.parseInt(status[3]);
                        boty = Integer.parseInt(status[4]);

                        savedAngle = Angle(currentX, currentY, botx, boty)+90;
                    }



                }

                //now we have a connection to the server
            } catch (UnknownHostException e) {
                System.err.println("Don't know about host: localhost.");
                e.printStackTrace();
                //System.exit(1);
            } catch (IOException e) {
                System.err.println("Couldn't get I/O for "
                        + "the connection to: localhost");
                e.printStackTrace();
                //System.exit(1);
            }
        }
        public int Angle(int x1, int y1, int x2, int y2) {
            float dx = (float) (x2-x1);
            float dy = (float) (y2-y1);
            double angle=0.0d;

            // Calculate angle
            if (dx == 0.0) {
                if (dy == 0.0)
                    angle = 0.0;
                else if (dy > 0.0)
                    angle = Math.PI / 2.0;
                else
                    angle = Math.PI * 3.0 / 2.0;
            } else if (dy == 0.0) {
                if  (dx > 0.0)
                    angle = 0.0;
                else
                    angle = Math.PI;
            } else {
                if  (dx < 0.0)
                    angle = Math.atan(dy/dx) + Math.PI;
                else if (dy < 0.0)
                    angle = Math.atan(dy/dx) + (2*Math.PI);
                else
                    angle = Math.atan(dy/dx);
            }

            // Convert to degrees
            angle = angle * 180 / Math.PI;

            // Return
            return (int) angle;
        }

    }
}
