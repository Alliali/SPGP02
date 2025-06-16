package com.example.tilesurvivor.game;

import android.graphics.Rect;

import com.example.tilesurvivor.R;

import kr.ac.tukorea.ge.spgp2025.a2dg.framework.objects.SheetSprite;

public class Monster extends SheetSprite {
    private static Rect[][] rects_array;

    public enum Type {
        pawn, king, knight, rook, bishop, queen
    }
    public Monster(Type type) {
        super(R.mipmap.monsterpieces, 1.0f);
        if (rects_array == null) {
            int type_count = Type.values().length;
            rects_array = new Rect[type_count][];
            int x = 0;
            int offset = 33;
            for (int i = 0; i < type_count; i++) {
                rects_array[i] = new Rect[1];
                for (int j = 0; j < 1; j++) {
                    rects_array[i][j] = new Rect(x, 0, x + offset, 42);
                    x += offset;
                }
            }
        }

        setPosition(0,0,100,100);
        srcRects = rects_array[type.ordinal()];
    }
}
