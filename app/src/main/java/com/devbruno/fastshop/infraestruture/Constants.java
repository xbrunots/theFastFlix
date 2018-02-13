package com.devbruno.fastshop.infraestruture;

import com.devbruno.fastshop.presentation.home.HomeActivity;

/**
 * Created by bsilvabr on 10/02/2018.
 */

public class Constants {
    public static final String API_KEY = "a7e80a5116f8513c2a2e9eebaf100e89";
    public static final String ERRO_TOOLBAR = "erro_toolbar";
    public static final String HOME_TITLE = "Home";
    public static final String ARGUMENT_TITLE = "ARGUMENT_TITLE";
    public static final String ARGUMENT_MOVIES = "ARGUMENT_MOVIES";
    public static final String ARGUMENT_GENRES = "ARGUMENT_GENRES";
    public static final String ERROR_API = "Chave da API invalida";
    public static final String TAG_GENERIC = "ErroGenerico";
    public static final String ERROR_UI = "Erro ao carregar layout";
    public static final String BASE_POSTER_URL = "http://image.tmdb.org/t/p/w185/";
    public static final String BASE_URL = "http://api.themoviedb.org/3/";
    public static final String BUTTON_BUY_TITLE = "OBTER AGORA";
    public static final String BUTTON_RET_TITLE = "SIM ALUGAR AGORA";
    public static final String TAG_MOVIE_ITEM = "FRAG_MENU_ITEM";
    //db variables
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "BitmapCache2.db";
    public static final String SQL_CREATE_ENTRIES = "CREATE TABLE bitmaps (key TEXT, bitmap BLOB)";
    public static final String SQL_DELETE_ENTRIES = "DELETE FROM bitmaps WHERE 1";
    public static final String GENRES_NAME = "GENRES";

    public enum TransationType {
        TYPE_BUY,
        TYPE_RET
    }

}
