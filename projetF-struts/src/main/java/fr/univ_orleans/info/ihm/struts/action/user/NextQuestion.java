package fr.univ_orleans.info.ihm.struts.action.user;

import fr.univ_orleans.info.ihm.modele.beans.IQuestion;
import fr.univ_orleans.info.ihm.modele.beans.IReponse;
import fr.univ_orleans.info.ihm.struts.action.def.ServiceAndSessionAwareAction;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import java.rmi.RemoteException;
import java.util.Calendar;
import java.util.List;

@ParentPackage(value = "user")
@Namespace(value = "/user")
public class NextQuestion extends ServiceAndSessionAwareAction{
    private static final Logger LOGGER = Logger.getLogger(BeginQCM.class.getCanonicalName());
    //Données pour la fin du QCM
    private int score;
    private int scoreMax;
    //Données formulaire
    private int[] checkboxList;
    private int reponse = -1;
    private int idQCM;
    //Question
    private int idQuestion;
    private int dureeQuestion;
    private int pointQuestion;
    private boolean multipleQuestion;
    private String intituleQuestion;
    private List<IReponse> reponses;

    @Action(value = "nextQuestion", results = {
            @Result(type = "tiles", location = "user/question.tiles"),
            @Result(name = "endQCM", type = "tiles", location = "user/endQCM.tiles")
    })
    @Override
    public String execute() {
        try {
            int idResultatUtilisateur = (int) this.getSession().get("idResultatUtilisateur");
            //On vérifie que le temps n'est pas dépassé
            long now = Calendar.getInstance().getTime().getTime();
            long before = (long) this.getSession().get("beforeQuestion");
            int secondQuestion = (int) this.getSession().get("dureeQuestion");
            //On donne 2 secondes supplémentaires pour les problèmes de latences
            if(now - before < (secondQuestion + 2) * 1000) {
                if (this.multipleQuestion) {
                    //Si la question est multiple on enregistre toutes les réponses données (s'il y en a)
                    if (this.checkboxList != null) {
                        for (int id : this.checkboxList) {
                            this.getModeleService().ajoutReponseResultatUtilisateur(idResultatUtilisateur, id);
                        }
                    }
                } else {
                    //Sinon il n'y a qu'une seule réponse à enregistrer (si il y a eu une réponse)
                    if (this.reponse != -1) {
                        this.getModeleService().ajoutReponseResultatUtilisateur(idResultatUtilisateur, this.reponse);
                    }
                }
            }
            //Récupération de la question suivante
            IQuestion question = this.getModeleService().getNextQuestionQCM(idQCM,idResultatUtilisateur);
            if(question == null){
                this.score = this.getModeleService().calculerScoreResultatUtilisateur(idResultatUtilisateur).getScore();
                this.scoreMax = this.getModeleService().calculerScoreMaxQCM(idQCM);
                return "endQCM";
            }else {
                this.idQuestion = question.getIdQuestion();
                this.pointQuestion = question.getPointQuestion();
                this.multipleQuestion = true;
                this.intituleQuestion = question.getIntituleQuestion();
                this.reponses = question.getReponses();
                this.dureeQuestion = question.getDureeQuestion();
                //Variable de session pour éviter la triche
                this.getSession().put("dureeQuestion", this.dureeQuestion);
                this.getSession().put("beforeQuestion", Calendar.getInstance().getTime().getTime());
            }
        } catch (RemoteException e) {
            LOGGER.fatal(e);
        }
        return SUCCESS;
    }

    public int getIdQCM() {
        return idQCM;
    }

    public void setIdQCM(int idQCM) {
        this.idQCM = idQCM;
    }

    public int[] getCheckboxList() {
        return checkboxList;
    }

    public void setCheckboxList(int[] checkboxList) {
        this.checkboxList = checkboxList;
    }

    public int getReponse() {
        return reponse;
    }

    public void setReponse(int reponse) {
        this.reponse = reponse;
    }

    public void setMultipleQuestion(boolean multipleQuestion) {
        this.multipleQuestion = multipleQuestion;
    }

    public int getIdQuestion() {
        return idQuestion;
    }

    public int getDureeQuestion() {
        return dureeQuestion;
    }

    public int getPointQuestion() {
        return pointQuestion;
    }

    public boolean isMultipleQuestion() {
        return multipleQuestion;
    }

    public String getIntituleQuestion() {
        return intituleQuestion;
    }

    public List<IReponse> getReponses() {
        return reponses;
    }

    public int getScoreMax() {
        return scoreMax;
    }

    public int getScore() {
        return score;
    }
}
