package com.scsse.workflow.util;

import com.scsse.workflow.entity.Graph;
import com.scsse.workflow.entity.Vector;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @author Alfred Fu
 * Created on 2019-02-21 19:26
 */
@Component
public class GraphOperation {



    public static List<Vector> topologicalSort(Graph graph){
        List<Vector> result = new ArrayList<>();

        // just make sure the first cycle can be procedure
        int lastCircleNumber = graph.size()+1;
        // in case of ultimate cycle
        while (graph.size()!=lastCircleNumber){
            lastCircleNumber=graph.size();
            //
            Vector theNodeNeedToBeRemoved = null;
            //
            for (Vector each : graph.getVectors()){
                if (each.getLastVectors().isEmpty()){
                    // remove itself from the node it points to
                    for (Vector temp:each.getNextVectors()){
                        temp.getLastVectors().remove(each);
                    }
                    // add it to the result
                    result.add(each);
                    // remove itself from the list later
                    theNodeNeedToBeRemoved = each;
                    break;
                }
            }
            // remove itself from the list
            if (theNodeNeedToBeRemoved!=null)
                graph.getVectors().remove(theNodeNeedToBeRemoved);
        }

        return result;
    }
}
