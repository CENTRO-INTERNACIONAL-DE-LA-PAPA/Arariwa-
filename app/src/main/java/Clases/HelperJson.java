package Clases;

import android.content.Context;
import com.cip.appcip.R;

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class HelperJson {
    private static String leerArchivo(Context context) throws Exception{
        StringBuffer sb = new StringBuffer();
        BufferedReader br = null;
        try{
            br = new BufferedReader(new InputStreamReader(context.getResources().openRawResource(R.raw.jsondata)));
            String line;
            while((line = br.readLine()) != null){
                sb.append(line);
            }
        }catch (Exception e){
            if(br != null)br.close();
            throw  e;
        }
        if(br != null)br.close();
        return sb.toString();
    }

    private static JSONObject getJSON(Context context)throws Exception{
        String sjson = leerArchivo(context);
        json =  new JSONObject(sjson);
        return json ;
    }
    private static JSONObject json = null;
    public static void Cargar(Context context, int midioma)throws Exception {
        getJSON(context);
        InfoVariedad.IDIOMA =  midioma;
        InfoVariedad.Titulo = json.getString(midioma==1?"Titulo":"Title");
        InfoVariedad.SubTituloMain = json.getString(midioma==1?"Paso1":"Step1");
        JSONArray jsonArrayVariedadResistente = json.getJSONArray("VariedadResistente");
        for (int i=0; i < jsonArrayVariedadResistente.length(); i++){
            JSONObject jitem = (JSONObject)jsonArrayVariedadResistente.get(i);
            if(midioma != jitem.getInt("Idioma"))continue;
            InfoVariedad.VariedadResistente.Titulo = jitem.getString("Titulo");
            InfoVariedad.VariedadResistente.SubTitulo = jitem.getString("SubTitulo");
            InfoVariedad.VariedadResistente.PeruVariedad = jitem.getString("PeruVariedad");
            InfoVariedad.VariedadResistente.EcuadorVariedad = jitem.getString("EcuadorVariedad");
            InfoVariedad.VariedadResistente.MetodoEvaluacion =jitem.getString("MetodoEvaluacion");
            InfoVariedad.VariedadResistente.QueAplicar = jitem.getString("QueAplicar");
            InfoVariedad.VariedadResistente.QueAplicar2 = jitem.getString("QueAplicar2");
        }
        jsonArrayVariedadResistente = json.getJSONArray("VariedadIntermedio");
        for (int i=0; i < jsonArrayVariedadResistente.length(); i++){
            JSONObject jitem = (JSONObject)jsonArrayVariedadResistente.get(i);
            if(midioma != jitem.getInt("Idioma"))continue;
            InfoVariedad.VariedadIntermedio.Titulo = jitem.getString("Titulo");
            InfoVariedad.VariedadIntermedio.SubTitulo = jitem.getString("SubTitulo");
            InfoVariedad.VariedadIntermedio.PeruVariedad = jitem.getString("PeruVariedad");
            InfoVariedad.VariedadIntermedio.EcuadorVariedad = jitem.getString("EcuadorVariedad");
            InfoVariedad.VariedadIntermedio.MetodoEvaluacion =jitem.getString("MetodoEvaluacion");
            InfoVariedad.VariedadIntermedio.QueAplicar = jitem.getString("QueAplicar");
            InfoVariedad.VariedadIntermedio.QueAplicar2 = jitem.getString("QueAplicar2");
        }
        jsonArrayVariedadResistente = json.getJSONArray("VariedadVulnerable");
        for (int i=0; i < jsonArrayVariedadResistente.length(); i++){
            JSONObject jitem = (JSONObject)jsonArrayVariedadResistente.get(i);
            if(midioma != jitem.getInt("Idioma"))continue;
            InfoVariedad.VariedadVulnerable.Titulo = jitem.getString("Titulo");
            InfoVariedad.VariedadVulnerable.SubTitulo = jitem.getString("SubTitulo");
            InfoVariedad.VariedadVulnerable.PeruVariedad = jitem.getString("PeruVariedad");
            InfoVariedad.VariedadVulnerable.EcuadorVariedad = jitem.getString("EcuadorVariedad");
            InfoVariedad.VariedadVulnerable.MetodoEvaluacion =jitem.getString("MetodoEvaluacion");
            InfoVariedad.VariedadVulnerable.QueAplicar = jitem.getString("QueAplicar");
            InfoVariedad.VariedadVulnerable.QueAplicar2 = jitem.getString("QueAplicar2");
        }
        jsonArrayVariedadResistente = json.getJSONArray("FungicidaAplicar");
        for (int i=0; i < jsonArrayVariedadResistente.length(); i++){
            JSONObject jitem = (JSONObject)jsonArrayVariedadResistente.get(i);
            if(midioma != jitem.getInt("Idioma"))continue;
            InfoVariedad.FungicidaAplicar.clear();
            InfoVariedad.FungicidaAplicar.add(jitem.get("Valor1").toString());
            InfoVariedad.FungicidaAplicar.add(jitem.get("Valor2").toString());
            InfoVariedad.FungicidaAplicar.add(jitem.get("Valor3").toString());
        }

        jsonArrayVariedadResistente = json.getJSONArray("Ayuda");
        for (int i=0; i < jsonArrayVariedadResistente.length(); i++){
            JSONObject jitem = (JSONObject)jsonArrayVariedadResistente.get(i);
            if(midioma != jitem.getInt("Idioma"))continue;
            String scolor = "";
            if(InfoVariedad.VARIEDAD_RULETA == InfoVariedad.VARIEDAD_RESISTENTE)
                scolor = midioma == 1?"Verde" : "green";
            if(InfoVariedad.VARIEDAD_RULETA == InfoVariedad.VARIEDAD_INTERMEDIO)
                scolor = midioma == 1?"amarillo" : "yellow";
            if(InfoVariedad.VARIEDAD_RULETA == InfoVariedad.VARIEDAD_VULNERABLE)
                scolor = midioma == 1?"rojo" : "red";
            InfoVariedad.Ayudas.clear();
            InfoVariedad.Ayudas.add(jitem.get("Paso1").toString().replace("[color]", scolor.toLowerCase()));
            InfoVariedad.Ayudas.add(jitem.get("Paso2").toString());
            InfoVariedad.Ayudas.add(jitem.get("Paso3").toString());
        }
    }
}