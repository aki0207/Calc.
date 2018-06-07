import java.io.*;
import java.util.*;

class CalcDentaku {

	public static void main(String[] args) {
		ArrayList<String> w_expression_field = new ArrayList<String>();
		int w_mult_result = 0;
		int w_division_result = 0;
		int w_add_result = 0;
		int w_judge_count = 0; // 奇数回、偶数回の判定に使う
		boolean w_judge_finish = true;//終わるかどうかの判定
		String w_changed_answer = " ";

		try {
		

		while (w_judge_finish){//計算後の質問でnが入力されれば抜ける
		while (true) {//"="が入力されれば抜ける

			boolean w_number_judge = true; // 数字かどうかの判定に使う
			boolean w_operand_judge = true; // 演算子かどうかの判定
			String w_changed_input_number = " "; //入力された文字の変換先
			String w_changed_input_operand = " ";
			if (w_judge_count % 2 == 0) { // 偶数回なら数字か判定
				while (w_number_judge) { // 数字が入力されない限りループ
					System.out.println("数字を入力してください");
					BufferedReader w_input_number = new BufferedReader(new InputStreamReader(System.in));
					
						w_changed_input_number = w_input_number.readLine();
					
						
					

					w_number_judge = isJudgeNumber(w_changed_input_number); // falseが返ってこれば抜けれる

				}
				w_expression_field.add(w_changed_input_number);// 入力された値を代入


				isDisp (w_expression_field);

			} else if (w_judge_count % 2 != 0) { // 奇数回なら演算子か判定
				while (w_operand_judge) {
					System.out.println("演算子を入力してください");
					BufferedReader w_input_operand = new BufferedReader(new InputStreamReader(System.in));
					
						w_changed_input_operand = w_input_operand.readLine();
					
					

					w_operand_judge = isJudgeOperand(w_changed_input_operand, w_judge_count); // falseが返ってこれば抜けれる
				}
				if (w_changed_input_operand.equals("=")) {//=が入力された時点でコチラからの入力は終わり。計算に移る。
				break;
				}

				w_expression_field.add(w_changed_input_operand);

				isDisp(w_expression_field);


			}
			
			

			w_judge_count++;
		}

		for (int i=0;i<w_expression_field.size();i++) {//入力された文字を調べる。*があればその前後1文字を掛け算
			  String w_check = w_expression_field.get(i);
			if (w_check.equals("*")) {
				w_mult_result = Integer.parseInt(w_expression_field.get(w_expression_field.indexOf("*") - 1))
						* Integer.parseInt(w_expression_field.get(w_expression_field.indexOf("*") + 1));

				w_expression_field.set(w_expression_field.indexOf("*") + 1, String.valueOf(w_mult_result));

				w_expression_field.remove(w_expression_field.indexOf("*") - 1);
				w_expression_field.remove(w_expression_field.indexOf("*"));

				i--;

			} else if (w_check.equals("/")) {//割り算Ver.
				w_division_result = Integer.parseInt(w_expression_field.get(w_expression_field.indexOf("/") - 1))
						/ Integer.parseInt(w_expression_field.get(w_expression_field.indexOf("/") + 1));

				w_expression_field.set(w_expression_field.indexOf("/") + 1, String.valueOf(w_division_result));

				w_expression_field.remove(w_expression_field.indexOf("/") - 1);
				w_expression_field.remove(w_expression_field.indexOf("/"));

				i--;
			}
		}

		for (int j=0;j<w_expression_field.size();j++) {//足し算Ver.
			 String w_last_check = w_expression_field.get(j);
			if (w_last_check.equals("+")) {
				w_add_result = Integer.parseInt(w_expression_field.get(w_expression_field.indexOf("+") - 1))
						+ Integer.parseInt(w_expression_field.get(w_expression_field.indexOf("+") + 1));

				w_expression_field.set(w_expression_field.indexOf("+") + 1, String.valueOf(w_add_result));

				w_expression_field.remove(w_expression_field.indexOf("+") - 1);
				w_expression_field.remove(w_expression_field.indexOf("+"));

				j--;

			} else if (w_last_check.equals("-")) {//引き算Ver.
				w_division_result = Integer.parseInt(w_expression_field.get(w_expression_field.indexOf("-") - 1))
						- Integer.parseInt(w_expression_field.get(w_expression_field.indexOf("-") + 1));

				w_expression_field.set(w_expression_field.indexOf("-") + 1, String.valueOf(w_division_result));

				w_expression_field.remove(w_expression_field.indexOf("-") - 1);
				w_expression_field.remove(w_expression_field.indexOf("-"));
 
				j--;
			}
		}
		for (String w_answer_disp : w_expression_field){
			System.out.println("答えは" + w_answer_disp);
		}

		w_expression_field.clear();  //配列初期化
		w_judge_count = 0;         //カウント変数初期化

		System.out.println("まだ計算します?(y or n)");

		BufferedReader w_input_judge = new BufferedReader(new InputStreamReader(System.in));


			w_changed_answer =  w_input_judge.readLine();

  
		 w_judge_finish = isJudgeFinish(w_changed_answer);
	}


	}catch (Exception e){
		e.printStackTrace();//エラーが発生したときの詳細が分かる

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
		if (pCount < 3 && pOperand.equals("=")) { // 最初に=を入力されないように
			return true;
		} else if (pOperand.equals("+") || pOperand.equals("-") || pOperand.equals("*") || pOperand.equals("/")
				|| pOperand.equals("=")) { // 演算子ならfalseを返し、ループを抜ける
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
			System.out.println("計算でもして頭を冷やされては?");
			return true;
		}
	}

	public static void isDisp (ArrayList<String> pArray){
		System.out.println ("現在入力されているのは");

		for (String w_current_status_disp : pArray){

			System.out.print(w_current_status_disp);

		}

		System.out.println("");//横向きに表示させるため
	}

}