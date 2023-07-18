package com.harena.eval_v1.services;

import com.harena.eval_v1.dao.ActeDao;
import com.harena.eval_v1.dao.DashRecetteDao;
import com.harena.eval_v1.models.Acte;
import com.harena.eval_v1.models.DashRecette;
import org.yaml.snakeyaml.nodes.AnchorNode;

import java.util.ArrayList;
import java.util.List;

public class DashRecetteService {

    public static DashRecetteDao dashRecetteDao = new DashRecetteDao();
    public static ActeDao acteDao = new ActeDao();
    public static ActeService acteService = new ActeService();



}
