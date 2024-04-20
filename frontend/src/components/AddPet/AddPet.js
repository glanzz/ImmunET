"use client";
import Link from "next/link";

const AddPet = () => {
  return (
    <div className="">
      {/* top section design */}
      <div className="flex sm:gap-6 gap:6 items-center sm:mb-0 mb-4 px-4 pb-5 mt-5">
        <div className="sm:mr-0 mr-4 ">
          <h2 className="font-bold sm:text-lg text-sm uppercase text-bold_text_colour">
            {" "}
            ADD PET
          </h2>
        </div>
      </div>
      {/* top section design */}

      <div className="lg:grid lg:grid-cols-9">
        <div className="col-span-7">
          <div className=" mx-auto  px-12 lg:px-20">
            <form>
              <div className="bg-primary_colour py-5">
                <h2 className="text-center text-xl text-white font-semibold">
                  ADD PET
                </h2>
              </div>
              <div className="bg-white shadow-md rounded px-8 pt-6 pb-8 mb-4 flex flex-col">
                <div className="-mx-3 md:flex mb-2 mt-4">
                  <div className="md:w-full px-3">
                    <label
                      className="uppercase tracking-wide text-black text-xs font-bold mb-2"
                      for="application-link"
                    >
                      Name
                    </label>
                    <input
                      className="w-full bg-gray-200 text-black border border-gray-200 rounded py-3 px-4 mb-3"
                      id="application-link"
                      type="text"
                      placeholder="Enter Name"
                    />
                  </div>
                </div>
                {/* title of product */}
                <div className="-mx-3 md:flex mb-6">
                  <div className="md:w-1/2 px-3 mb-6 md:mb-0">
                    <label
                      className="uppercase  text-black text-xs font-bold mb-2"
                      for="company"
                    >
                      Species
                    </label>
                    <input
                      className="w-full bg-gray-200 text-black border border-gray-200 rounded py-3 px-4 mb-3"
                      id="company"
                      type="text"
                      placeholder=" Species"
                    />
                    <div>
                      <span className="text-red-500 text-xs italic">
                        Please fill out this field.
                      </span>
                    </div>
                  </div>
                  <div className="md:w-1/2 px-3">
                    <label
                      className="uppercase tracking-wide text-black text-xs font-bold mb-2"
                      for="title"
                    >
                      Gender
                    </label>
                    <input
                      className="w-full bg-gray-200 text-black border border-gray-200 rounded py-3 px-4 mb-3"
                      id="title"
                      type="text"
                      placeholder="Gender"
                    />
                  </div>
                </div>
                <div className="-mx-3 md:flex mb-6">
                  <div className="md:w-1/2 px-3 mb-6 md:mb-0">
                    <label
                      className="uppercase  text-black text-xs font-bold mb-2"
                      for="company"
                    >
                      Date Of Birth
                    </label>
                    <input
                      className="w-full bg-gray-200 text-black border border-gray-200 rounded py-3 px-4 mb-3"
                      id="company"
                      type="text"
                      placeholder="Date Of Birth"
                    />
                    <div>
                      <span className="text-red-500 text-xs italic">
                        Please fill out this field.
                      </span>
                    </div>
                  </div>
                  <div className="md:w-1/2 px-3">
                    <label
                      className="uppercase tracking-wide text-black text-xs font-bold mb-2"
                      for="title"
                    >
                      Contact
                    </label>
                    <input
                      className="w-full bg-gray-200 text-black border border-gray-200 rounded py-3 px-4 mb-3"
                      id="title"
                      type="text"
                      placeholder="Contact"
                    />
                  </div>
                </div>

                <div className="mt-2">
                  <Link href="#">
                    <button className="bg-custom_button_color w-full  text-black uppercase font-semibold  relative h-[50px] border px-3 transition-all before:absolute before:bottom-0 before:left-0 before:top-0 before:w-full before:h-0 before:bg-primary_colour before:transition-all before:duration-700 hover:text-white  hover:before:top-0 hover:before:h-full">
                      <span className="relative z-10">Continue</span>
                    </button>
                  </Link>
                </div>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
  );
};

export default AddPet;
