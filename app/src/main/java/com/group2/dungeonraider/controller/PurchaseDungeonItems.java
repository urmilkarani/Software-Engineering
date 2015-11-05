package com.group2.dungeonraider.controller;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.test.dungeonmainmenu.R;
import com.group2.dungeonraider.data.DatabaseHelper;
import com.group2.dungeonraider.domain.Item;
import com.group2.dungeonraider.domain.Mutator;
import com.group2.dungeonraider.domain.Player;
import com.group2.dungeonraider.utilities.Constants;

import java.util.List;

/**
 * Created by ukara on 10/28/2015.
 */
public class PurchaseDungeonItems extends Activity {

    DatabaseHelper databaseHelper = new DatabaseHelper(Constants.appContext);
    ;
    Player p = Player.getInstance();
    List<Item> lstItem = databaseHelper.getAllItems();
    int mapCost = Constants.ITEM_MAP_VALUE;
    int keyCost = Constants.ITEM_KEY_VALUE;
    int bombCost = Constants.ITEM_BOMB_VALUE;
    int potionCost = Constants.ITEM_POTION_VALUE;
    TextView textViewGold;
    TextView textViewKeys;
    TextView textViewPotion;
    TextView textViewBombs;
    int potionCount = p.getItemCount(Constants.ITEM_POTION);
    int keyCount = p.getItemCount(Constants.ITEM_KEY);
    int bombsCount = p.getItemCount(Constants.ITEM_BOMB);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("PurchaseDungeonItems", "onCreate called");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.purchase_dungeon_items);
        textViewGold = (TextView) findViewById(R.id.textView_goldvalue);
        textViewGold.setText(Integer.toString(p.getGold()));
        textViewKeys = (TextView) findViewById(R.id.textViewKeysOwned);
        textViewKeys.setText(Integer.toString(keyCount));
        textViewKeys.setGravity(Gravity.CENTER);
        textViewPotion = (TextView) findViewById(R.id.textViewPotionsOwned);
        textViewPotion.setText(Integer.toString(potionCount));
        textViewPotion.setGravity(Gravity.CENTER);
        textViewBombs = (TextView) findViewById(R.id.textViewBombsOwned);
        textViewBombs.setText(Integer.toString(bombsCount));
        textViewBombs.setGravity(Gravity.CENTER);

    }

    public void buydungeonmap(View v) {
        Log.d("PurchaseDungeonItems", "buydungeonmap called");
        if (p.getGold() >= mapCost) {
            p.setGold(p.getGold() - mapCost);
            databaseHelper.updatePlayerGoldValue(p);
            TextView textView = (TextView) findViewById(R.id.textView_goldvalue);
            textView.setText(Integer.toString(p.getGold()));
        } else {
            Toast.makeText(this, "Not enough gold to purchase map", Toast.LENGTH_LONG).show();
        }

    }

    public void buydungeonkey(View v) {
        Log.d("PurchaseDungeonItems", "buydungeonkey called");
        if (p.getGold() >= keyCost) {
            p.setGold(p.getGold() - keyCost);
            p.setItemCount(Constants.ITEM_KEY, p.getItemCount(Constants.ITEM_KEY) + 1);
            databaseHelper.updatePlayerGoldValue(p);
            databaseHelper.updatePlayerItemCount(Constants.ITEM_KEY,getItemId(Constants.ITEM_KEY));
            textViewGold = (TextView) findViewById(R.id.textView_goldvalue);
            textViewGold.setText(Integer.toString(p.getGold()));
            textViewKeys = (TextView) findViewById(R.id.textViewKeysOwned);
            textViewKeys.setText(Integer.toString(p.getItemCount(Constants.ITEM_KEY)));
        } else {
            Toast.makeText(this, "Not enough gold to purchase key", Toast.LENGTH_LONG).show();
        }
    }

    public void buydungeonpotion(View v) {
        Log.d("PurchaseDungeonItems", "buydungeonoptions called");
        if (p.getGold() >= potionCost) {
            p.setGold(p.getGold() - potionCost);
            p.setItemCount(Constants.ITEM_POTION, p.getItemCount(Constants.ITEM_POTION) + 1);
            databaseHelper.updatePlayerGoldValue(p);
            databaseHelper.updatePlayerItemCount(Constants.ITEM_POTION,getItemId(Constants.ITEM_POTION));
            textViewGold = (TextView) findViewById(R.id.textView_goldvalue);
            textViewGold.setText(Integer.toString(p.getGold()));
            textViewPotion = (TextView) findViewById(R.id.textViewPotionsOwned);
            textViewPotion.setText(Integer.toString(p.getItemCount(Constants.ITEM_POTION)));
        } else {
            Toast.makeText(this, "Not enough gold to purchase potion", Toast.LENGTH_LONG).show();
        }
    }

    public void buydungeonbombs(View v) {
        Log.d("PurchaseDungeonsItems", "buydungeonbombs Called");
        if (p.getGold() >= bombCost) {
            p.setGold(p.getGold() - bombCost);
            p.setItemCount(Constants.ITEM_BOMB, p.getItemCount(Constants.ITEM_BOMB) + 1);
            databaseHelper.updatePlayerGoldValue(p);
            databaseHelper.updatePlayerItemCount(Constants.ITEM_BOMB, getItemId(Constants.ITEM_BOMB));
            textViewGold = (TextView) findViewById(R.id.textView_goldvalue);
            textViewGold.setText(Integer.toString(p.getGold()));
            textViewBombs = (TextView) findViewById(R.id.textViewBombsOwned);
            textViewBombs.setText(Integer.toString(p.getItemCount(Constants.ITEM_BOMB)));
        } else {
            Toast.makeText(this, "Not enough gold to purchase bomb", Toast.LENGTH_LONG).show();
        }

    }

    public void backtostorescreen(View v) {
        Log.d("PurchaseDungeonsItems", "onBackPressed Called");
        PurchaseDungeonItems.this.finish();
    }

    public int getItemId(String name) {

        int value = 0;
        for (Item item : lstItem) {
            if (item.getName().equals(name)) {
                value = item.getId();

            }
        }
        return value;
    }
}