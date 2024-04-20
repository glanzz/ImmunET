import Image from "next/image";
import imglogo from "../../../public/assets/detailglogo.png";
import dog from "../../../public/assets/dog.jpg";
export default function PetDetailsInfo({ pet }) {
  return (
    <div>
      <div className="flex gap-12 bg-primary_colour py-4 px-10 mb-5">
        <div>
          <Image src={imglogo} alt="img" />
        </div>
        <div className=" ">
          <h3 className="text-center text-3xl font-extrabold text-white">
            Dog Shot Details
          </h3>
          <p className="text-white text-center font-normal">
            {pet?.owner?.address}
          </p>
          <div className="flex gap-4 items-center justify-center">
            <p className="text-white text-center font-normal">
              Name: {pet?.name}
            </p>
            {/* <p className="text-white text-center font-normal">
              email:test@gmail.com
            </p> */}
          </div>
        </div>
      </div>
      <div className="bg-primary_colour p-4">
        <h2 className="text-lg font-bold text-white uppercase text-center">
          Pet Details
        </h2>
      </div>

      {/*  pet details desing
       */}
      <div className="grid grid-cols-7 py-4">
        <div className="md:w-full px-3 col-span-4">
          <div className="grid grid-cols-1 gap-4">
            <div className="col-span-1 text-left items-start">
              <h2 className="text-left justify-start items-start">
                <span className="font-semibold uppercase">Name:</span>
                {pet?.name}
              </h2>
            </div>
            {/*  <div className="col-span-1 ">
              <h2 className="text-left justify-start items-start text-xl ">
                <span className="font-semibold uppercase"> Gender:</span>
                {pet.}
              </h2>
            </div> */}
            <div className="col-span-1">
              <h2 className="text-left justify-start items-start text-xl ">
                <span className="font-semibold uppercase"> Birth Date:</span>
                {pet?.dob}
              </h2>
            </div>
            <div className="col-span-1">
              <h2 className="text-left justify-start items-start text-xl ">
                <span className="font-semibold uppercase"> species:</span>
                {pet.species}
              </h2>
            </div>
            <div className="col-span-1">
              <h2 className="text-left justify-start items-start text-xl ">
                <span className="font-semibold uppercase">owner:</span>
                {pet?.owner?.name}
              </h2>
            </div>
            <div className="col-span-1">
              <h2 className="text-left justify-start items-start text-xl ">
                <span className="font-semibold uppercase">Address:</span>
                {pet?.owner?.address}
              </h2>
            </div>
          </div>
        </div>
        <div className="col-span-3">
          <Image src={dog} alt="dog" />
        </div>
      </div>
      {/*  pet details desing
       */}
      <div className="bg-primary_colour p-4 mt-5">
        <h2 className="text-lg font-bold text-white uppercase text-center">
          Recommended Immunization Schedule
        </h2>
      </div>
    </div>
  );
}
