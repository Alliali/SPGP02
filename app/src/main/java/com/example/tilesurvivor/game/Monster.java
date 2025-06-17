package com.example.tilesurvivor.game;

import android.graphics.Rect;

import com.example.tilesurvivor.R;

import kr.ac.tukorea.ge.spgp2025.a2dg.framework.interfaces.IRecyclable;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.objects.SheetSprite;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.scene.Scene;

public class Monster extends SheetSprite implements IRecyclable {
    private static Rect[][] rects_array;

    @Override
    public void onRecycle() {

    }

    public enum Type {
        pawn, king, knight, rook, bishop, queen
    }
    public Monster() {
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
    }

    public static Monster get(Type type) {
        return Scene.top().getRecyclable(Monster.class).init(type);
    }

    public Monster init(Type type) {
        srcRects = rects_array[type.ordinal()];
        return this;
    }
}
