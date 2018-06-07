import java.io.*;
import java.util.*;

class CalcDentaku {

	public static void main(String[] args) {
		ArrayList<String> w_expression_field = new ArrayList<String>();
		int w_mult_result = 0;
		int w_division_result = 0;
		int w_add_result = 0;
		int w_judge_count = 0; // ���A������̔���Ɏg��
		boolean w_judge_finish = true;//�I��邩�ǂ����̔���
		String w_changed_answer = " ";

		try {
		

		while (w_judge_finish){//�v�Z��̎����n�����͂����Δ�����
		while (true) {//"="�����͂����Δ�����

			boolean w_number_judge = true; // �������ǂ����̔���Ɏg��
			boolean w_operand_judge = true; // ���Z�q���ǂ����̔���
			String w_changed_input_number = " "; //���͂��ꂽ�����̕ϊ���
			String w_changed_input_operand = " ";
			if (w_judge_count % 2 == 0) { // ������Ȃ琔��������
				while (w_number_judge) { // ���������͂���Ȃ����胋�[�v
					System.out.println("��������͂��Ă�������");
					BufferedReader w_input_number = new BufferedReader(new InputStreamReader(System.in));
					
						w_changed_input_number = w_input_number.readLine();
					
						
					

					w_number_judge = isJudgeNumber(w_changed_input_number); // false���Ԃ��Ă���Δ������

				}
				w_expression_field.add(w_changed_input_number);// ���͂��ꂽ�l����


				isDisp (w_expression_field);

			} else if (w_judge_count % 2 != 0) { // ���Ȃ牉�Z�q������
				while (w_operand_judge) {
					System.out.println("���Z�q����͂��Ă�������");
					BufferedReader w_input_operand = new BufferedReader(new InputStreamReader(System.in));
					
						w_changed_input_operand = w_input_operand.readLine();
					
					

					w_operand_judge = isJudgeOperand(w_changed_input_operand, w_judge_count); // false���Ԃ��Ă���Δ������
				}
				if (w_changed_input_operand.equals("=")) {//=�����͂��ꂽ���_�ŃR�`������̓��͂͏I���B�v�Z�Ɉڂ�B
				break;
				}

				w_expression_field.add(w_changed_input_operand);

				isDisp(w_expression_field);


			}
			
			

			w_judge_count++;
		}

		for (int i=0;i<w_expression_field.size();i++) {//���͂��ꂽ�����𒲂ׂ�B*������΂��̑O��1�������|���Z
			  String w_check = w_expression_field.get(i);
			if (w_check.equals("*")) {
				w_mult_result = Integer.parseInt(w_expression_field.get(w_expression_field.indexOf("*") - 1))
						* Integer.parseInt(w_expression_field.get(w_expression_field.indexOf("*") + 1));

				w_expression_field.set(w_expression_field.indexOf("*") + 1, String.valueOf(w_mult_result));

				w_expression_field.remove(w_expression_field.indexOf("*") - 1);
				w_expression_field.remove(w_expression_field.indexOf("*"));

				i--;

			} else if (w_check.equals("/")) {//����ZVer.
				w_division_result = Integer.parseInt(w_expression_field.get(w_expression_field.indexOf("/") - 1))
						/ Integer.parseInt(w_expression_field.get(w_expression_field.indexOf("/") + 1));

				w_expression_field.set(w_expression_field.indexOf("/") + 1, String.valueOf(w_division_result));

				w_expression_field.remove(w_expression_field.indexOf("/") - 1);
				w_expression_field.remove(w_expression_field.indexOf("/"));

				i--;
			}
		}

		for (int j=0;j<w_expression_field.size();j++) {//�����ZVer.
			 String w_last_check = w_expression_field.get(j);
			if (w_last_check.equals("+")) {
				w_add_result = Integer.parseInt(w_expression_field.get(w_expression_field.indexOf("+") - 1))
						+ Integer.parseInt(w_expression_field.get(w_expression_field.indexOf("+") + 1));

				w_expression_field.set(w_expression_field.indexOf("+") + 1, String.valueOf(w_add_result));

				w_expression_field.remove(w_expression_field.indexOf("+") - 1);
				w_expression_field.remove(w_expression_field.indexOf("+"));

				j--;

			} else if (w_last_check.equals("-")) {//�����ZVer.
				w_division_result = Integer.parseInt(w_expression_field.get(w_expression_field.indexOf("-") - 1))
						- Integer.parseInt(w_expression_field.get(w_expression_field.indexOf("-") + 1));

				w_expression_field.set(w_expression_field.indexOf("-") + 1, String.valueOf(w_division_result));

				w_expression_field.remove(w_expression_field.indexOf("-") - 1);
				w_expression_field.remove(w_expression_field.indexOf("-"));
 
				j--;
			}
		}
		for (String w_answer_disp : w_expression_field){
			System.out.println("������" + w_answer_disp);
		}

		w_expression_field.clear();  //�z�񏉊���
		w_judge_count = 0;         //�J�E���g�ϐ�������

		System.out.println("�܂��v�Z���܂�?(y or n)");

		BufferedReader w_input_judge = new BufferedReader(new InputStreamReader(System.in));


			w_changed_answer =  w_input_judge.readLine();

  
		 w_judge_finish = isJudgeFinish(w_changed_answer);
	}


	}catch (Exception e){
		e.printStackTrace();//�G���[�����������Ƃ��̏ڍׂ�������

	}

	}


	public static boolean isJudgeNumber(String pNumber) {
		try {
			Integer.parseInt(pNumber);
			return false;
		} catch (NumberFormatException e) {
			return true;
		}
	}

	public static boolean isJudgeOperand(String pOperand, int pCount) {
		if (pCount < 3 && pOperand.equals("=")) { // �ŏ���=����͂���Ȃ��悤��
			return true;
		} else if (pOperand.equals("+") || pOperand.equals("-") || pOperand.equals("*") || pOperand.equals("/")
				|| pOperand.equals("=")) { // ���Z�q�Ȃ�false��Ԃ��A���[�v�𔲂���
			return false;
		} else {
			return true;
		}
	}

	public static boolean isJudgeFinish (String pAnswer){
		if(pAnswer.equals("y")){
			return true;
		} else if (pAnswer.equals("n")){
			return false;
		} else {
			System.out.println("�v�Z�ł����ē����₳��Ă�?");
			return true;
		}
	}

	public static void isDisp (ArrayList<String> pArray){
		System.out.println ("���ݓ��͂���Ă���̂�");

		for (String w_current_status_disp : pArray){

			System.out.print(w_current_status_disp);

		}

		System.out.println("");//�������ɕ\�������邽��
	}

}