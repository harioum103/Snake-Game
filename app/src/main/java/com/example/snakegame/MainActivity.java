package com.example.snakegame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.example.snakegame.engine.GameEngine;
import com.example.snakegame.enums.GameState;
import com.example.snakegame.views.SnakeView;
import com.example.snakegame.views.SnakeView;

public class MainActivity extends AppCompatActivity {

    private GameEngine gameEngine;
    private SnakeView snakeView;
    private Handler handler =new Handler();
    private final long updateDelay =125;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gameEngine=new GameEngine();
        gameEngine.initGame();
        snakeView = (SnakeView)findViewById(R.id.snakeView);
        startUpdateHandler();

    }

    private void startUpdateHandler(){
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                gameEngine.Update();
                if(gameEngine.getCurrentGameState()== GameState.Running){
                    handler.postDelayed(this,updateDelay);
                }
                if(GameEngine.getCurrentGameState()==GameState.Lost){
                 onGameLost();
                }
                snakeView.setSnakeViewMap(gameEngine.getMap());
                snakeView.invalidate();
            }
        },updateDelay);
    }
     private void onGameLost(){
         Toast.makeText(this,"you lost",Toast.LENGTH_SHORT).show();
     }
}