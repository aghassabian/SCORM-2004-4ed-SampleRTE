/*******************************************************************************

ADL SCORM 2004 4th Edition Sample Run-Time Environment

The ADL SCORM 2004 4th Ed. Sample Run-Time Environment is licensed under
Creative Commons Attribution-Noncommercial-Share Alike 3.0 United States.

The Advanced Distributed Learning Initiative allows you to:
  *  Share - to copy, distribute and transmit the work.
  *  Remix - to adapt the work. 

Under the following conditions:
  *  Attribution. You must attribute the work in the manner specified by the author or
     licensor (but not in any way that suggests that they endorse you or your use
     of the work).
  *  Noncommercial. You may not use this work for commercial purposes. 
  *  Share Alike. If you alter, transform, or build upon this work, you may distribute
     the resulting work only under the same or similar license to this one. 

For any reuse or distribution, you must make clear to others the license terms of this work. 

Any of the above conditions can be waived if you get permission from the ADL Initiative. 
Nothing in this license impairs or restricts the author's moral rights.

*******************************************************************************/

package org.adl.sequencer;

import org.adl.util.debug.DebugIndicator;

import java.io.Serializable;

/**
 * <strong>Filename:</strong> SeqRule.java<br><br>
 * 
 * <strong>Description:</strong><br>
 * This is an implementation of sequencing rule behavior defined in section
 * 2.7 of the IMS SS Specification.  This class encapsulates one sequencing
 * rule and performs the evaluation of that rule.<br><br>
 * 
 * <strong>Design Issues:</strong><br>
 * This implementation is intended to be used by the 
 * SCORM 2004 4th Edition Sample RTE. <br>
 * <br>
 * 
 * <strong>Implementation Issues:</strong><br>
 * As with other classes that encapsulate sequencing behaviors, this class is
 * not optimized.  It is intended to demonstrate the intension of the
 * specification and not provide a 'full-featured' implementation.<br><br>
 * 
 * <strong>Known Problems:</strong><br><br>
 * 
 * <strong>Side Effects:</strong><br><br>
 * 
 * <strong>References:</strong><br>
 * <ul>
 *     <li>IMS SS 1.0</li>
 *     <li>SCORM 2004 4th Edition</li>
 * </ul>
 * 
 * @author ADL Technical Team
 */
public class SeqRule implements Serializable
{

   /**
    * Enumeration of possible sequencing rule actions -- 
    *    This value is only as a place holder.
    * <br>No Action 
    * <br><b>"noaction"</b>
    * <br>[INTERNAL SEQUENCING SUBSYSTEM CONSTANT]
    */
   public static String SEQ_ACTION_NOACTION            = "noaction";

   /**
    * Enumeration of possible sequencing rule actions -- described in Sequencing
    * Rule Description (SM.2) element 5 of the IMS SS Specification.
    * <br>Ignore
    * <br><b>"ignore"</b>
    * <br>[SEQUENCING SUBSYSTEM CONSTANT]
    */
   public static String SEQ_ACTION_IGNORE             = "ignore";

   /**
    * Enumeration of possible sequencing rule actions -- described in Sequencing
    * Rule Description (SM.2) element 5 of the IMS SS Specification.
    * <br>Skip
    * <br><b>"skip"</b>
    * <br>[SEQUENCING SUBSYSTEM CONSTANT]
    */
   public static String SEQ_ACTION_SKIP               = "skip";

   /**
    * Enumeration of possible sequencing rule actions -- described in Sequencing
    * Rule Description (SM.2) element 5 of the IMS SS Specification.
    * <br>Disabled
    * <br><b>"disabled"</b>
    * <br>[SEQUENCING SUBSYSTEM CONSTANT]
    */
   public static String SEQ_ACTION_DISABLED           = "disabled";

   /**
    * Enumeration of possible sequencing rule actions -- described in Sequencing
    * Rule Description (SM.2) element 5 of the IMS SS Specification.
    * <br>Hide From Choice
    * <br><b>"hideFromChoice"</b>
    * <br>[SEQUENCING SUBSYSTEM CONSTANT]
    */
   public static String SEQ_ACTION_HIDEFROMCHOICE     = "hiddenFromChoice";

   /**
    * Enumeration of possible sequencing rule actions -- described in Sequencing
    * Rule Description (SM.2) element 5 of the IMS SS Specification.
    * <br>Stop Forward Traversal
    * <br><b>"stopForwardTraversal"</b>
    * <br>[SEQUENCING SUBSYSTEM CONSTANT]
    */
   public static String SEQ_ACTION_FORWARDBLOCK       = "stopForwardTraversal";

   /**
    * Enumeration of possible sequencing rule actions -- described in Sequencing
    * Rule Description (SM.2) element 5 of the IMS SS Specification.
    * <br>Exit Parent
    * <br><b>"exitParent"</b>
    * <br>[SEQUENCING SUBSYSTEM CONSTANT]
    */
   public static String SEQ_ACTION_EXITPARENT         = "exitParent";

   /**
    * Enumeration of possible sequencing rule actions -- described in Sequencing
    * Rule Description (SM.2) element 5 of the IMS SS Specification.
    * <br>Exit All
    * <br><b>"exitAll"</b>
    * <br>[SEQUENCING SUBSYSTEM CONSTANT]
    */
   public static String SEQ_ACTION_EXITALL            = "exitAll";

   /**
    * Enumeration of possible sequencing rule actions -- described in Sequencing
    * Rule Description (SM.2) element 5 of the IMS SS Specification.
    * <br>Retry
    * <br><b>"retry"</b>
    * <br>[SEQUENCING SUBSYSTEM CONSTANT]
    */
   public static String SEQ_ACTION_RETRY              = "retry";

   /**
    * Enumeration of possible sequencing rule actions -- described in Sequencing
    * Rule Description (SM.2) element 5 of the IMS SS Specification.
    * <br>Retry All
    * <br><b>"retryAll"</b>
    * <br>[SEQUENCING SUBSYSTEM CONSTANT]
    */
   public static String SEQ_ACTION_RETRYALL           = "retryAll";

   /**
    * Enumeration of possible sequencing rule actions -- described in Sequencing
    * Rule Description (SM.2) element 5 of the IMS SS Specification.
    * <br>Continue
    * <br><b>"continue"</b>
    * <br>[SEQUENCING SUBSYSTEM CONSTANT]
    */
   public static String SEQ_ACTION_CONTINUE           = "continue";

   /**
    * Enumeration of possible sequencing rule actions -- described in Sequencing
    * Rule Description (SM.2) element 5 of the IMS SS Specification.
    * <br>Previous
    * <br><b>"previous"</b>
    * <br>[SEQUENCING SUBSYSTEM CONSTANT]
    */
   public static String SEQ_ACTION_PREVIOUS           = "previous";

   /**
    * Enumeration of possible sequencing rule actions -- described in Sequencing
    * Rule Description (SM.2) element 5 of the IMS SS Specification.
    * <br>Exit
    * <br><b>"exit"</b>
    * <br>[SEQUENCING SUBSYSTEM CONSTANT]
    */
   public static String SEQ_ACTION_EXIT               = "exit";

   /**
    * This controls display of log messages to the java console
    */
   private static boolean _Debug = DebugIndicator.ON;

   /**
    * This describes the rollup rule Child Activity Set (element 2.3)
    */
   public String mAction = SeqRule.SEQ_ACTION_IGNORE;

   /**
    * This describes the rollup rule conditions (element 2.2)
    */
   public SeqConditionSet mConditions = null;

   /**
    * Default constructor
    *
    */
   public SeqRule()
   {
      // Default constructor, does nothing explicitly
   }

   /**
    * This method provides the state this <code>SeqRule</code> object for
    * diagnostic purposes.
    */
   public void dumpState()
   {
      if ( _Debug )
      {
         System.out.println("  :: SeqRule       --> BEGIN - dumpState");

         System.out.println("  ::--> Action      : " + mAction);
         System.out.println("  ------------------- ");

         if ( mConditions != null )
         {
            mConditions.dumpState();
         }
         else
         {
            System.out.println("  ::-->  NULL conditions");
         }

         System.out.println("  :: SeqRule       --> END   - dumpState");
      }
   }

   /**
    * Evaluates this sequencing rule using its declared parameters.<br><br>
    * This is an implementation of UP.2.1.
    * 
    * @param iType         Indicates the type of rules to evaluation.  This 
    *                      acts as a filter on valid rules.
    * 
    * @param iThisActivity The activity defining this rule.
    * 
    * @param iRetry        Indicates that this rule is being evaluated during
    *                      a Retry sequencing request process.
    * 
    * @return The resulting action of the rule evaluation.  This will be a valid
    *         sequencing rule action or <code>SEQ_ACTION_NOACTION</code> if
    *         the rule evaluated to <code>false</code>.
    */
   String evaluate(int iType, SeqActivity iThisActivity, boolean iRetry)
   {

      if ( _Debug )
      {
         System.out.println("  :: SeqRule      --> BEGIN - evaluate");
         System.out.println("  ::-->  " + iType);
      }

      String result = SeqRule.SEQ_ACTION_NOACTION;
      boolean doEvaluation = false;

      // Filter the rule type prior to performing the evaluation.
      switch ( iType )
      {
         case SeqRuleset.RULE_TYPE_ANY:
         {
            doEvaluation = true;
            break;
         }
         case SeqRuleset.RULE_TYPE_POST:
         {
            if ( mAction.equals(SeqRule.SEQ_ACTION_EXITPARENT) || 
                 mAction.equals(SeqRule.SEQ_ACTION_EXITALL) ||
                 mAction.equals(SeqRule.SEQ_ACTION_RETRY) ||
                 mAction.equals(SeqRule.SEQ_ACTION_RETRYALL) ||
                 mAction.equals(SeqRule.SEQ_ACTION_CONTINUE) ||
                 mAction.equals(SeqRule.SEQ_ACTION_PREVIOUS) )
            {
               doEvaluation = true;
            }

            break;
         }
         case SeqRuleset.RULE_TYPE_EXIT:
         {
            if ( mAction.equals(SeqRule.SEQ_ACTION_EXIT) )
            {
               doEvaluation = true;
            }

            break;
         }
         case SeqRuleset.RULE_TYPE_SKIPPED:
         {
            if ( mAction.equals(SeqRule.SEQ_ACTION_SKIP) )
            {
               doEvaluation = true;
            }

            break;
         }
         case SeqRuleset.RULE_TYPE_DISABLED:
         {
            if ( mAction.equals(SeqRule.SEQ_ACTION_DISABLED) )
            {
               doEvaluation = true;
            }

            break; 
         }
         case SeqRuleset.RULE_TYPE_HIDDEN:
         {
            if ( mAction.equals(SeqRule.SEQ_ACTION_HIDEFROMCHOICE) )
            {
               doEvaluation = true;
            }

            break;  
         }
         case SeqRuleset.RULE_TYPE_FORWARDBLOCK:
         {
            if ( mAction.equals(SeqRule.SEQ_ACTION_FORWARDBLOCK) )
            {
               doEvaluation = true;
            }

            break;
         }
         default:
         {
            break;
         }
      }

      // Make sure the type of the current rule allows it to be evaluated.
      if ( doEvaluation )
      {
         // Make sure we have a valid target activity 
         if ( iThisActivity != null )
         {

            if ( _Debug )
            {
               System.out.println("  ::-->  Evaluating - " + mAction);
            }

            if ( mConditions.evaluate(iThisActivity, iRetry) ==
                 SeqConditionSet.EVALUATE_TRUE )
            {
               result = mAction;
            }
         }
      }

      if ( _Debug )
      {
         System.out.println("  ::-->  " + result); 
         System.out.println("  :: SeqRule      --> END   - evaluate");
      }

      return result;

   }

}  // end SeqRule
